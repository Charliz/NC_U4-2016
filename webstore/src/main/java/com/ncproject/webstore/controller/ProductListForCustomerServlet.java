package com.ncproject.webstore.controller;

import com.ncproject.webstore.entity.Cart;
import com.ncproject.webstore.entity.StoreCatalog;
import com.ncproject.webstore.ejb.CartBeanInterface;
import com.ncproject.webstore.ejb.CatalogBeanInterface;
import com.ncproject.webstore.ejb.CustomerBeanInterface;
import com.ncproject.webstore.entity.Customer;
import com.ncproject.webstore.utils.EncryptionUtil;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Черный on 22.02.2017.
 */
@WebServlet(urlPatterns = {"/customer/mts"})
public class ProductListForCustomerServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;
    @EJB
    private CartBeanInterface cartBean;
    @EJB
    private CustomerBeanInterface customerBean;
    @EJB
    private CatalogBeanInterface catalogBean;

    private String imagePath;

    @Override
    public void init() throws ServletException {
        this.imagePath = System.getProperty("jboss.server.data.dir");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Add our customer to session
        HttpSession s = req.getSession();
        //Object user = s.getAttribute("myUser");
        boolean loggedIn = s != null && s.getAttribute("myUser") != null;
       // System.out.println(user.toString() + "Object user!!");
//        Customer customer = customerBean.read(req.getRemoteUser()); //достает юзера из запроса
//                                                            // и тут как раз ошибка бинная
//        HttpSession session = req.getSession();
//        session.setAttribute("myUser", customer);
        System.out.println("WAS in SERVLET -------------------------------" + loggedIn);
        // read the hidden "command" parameter
        if (loggedIn) {
            String theCommand = req.getParameter("command");

            if ("ADD".equals(theCommand)) {
                addToCart(req, resp);
                listProducts(req, resp);
            } else {
                listProducts(req, resp);
            }
        }
        else{
            resp.sendRedirect("/webstore/loginIndex.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass = req.getParameter("j_password");
        String user = req.getParameter("j_username");
        Customer customer = customerBean.read(user);
        System.err.println("WAS IN login SERVLET POST");
        boolean hashedPass = false;
        try {
            hashedPass = EncryptionUtil.validatePassword(pass, customer.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println(hashedPass + "hashed pass");
        System.out.println(customer.getPassword() + "pass from db");

        if (user.equals(customer.getLogin()) && hashedPass ) {
//            req.getRequestDispatcher("/index.html").forward(req, resp);
            req.getSession().setAttribute("myUser", customer);
            resp.sendRedirect("/webstore/customer/mts");
        }
        else {
//            req.getRequestDispatcher("/error.html").forward(req, resp);
            resp.sendRedirect("/webstore/loginIndex.jsp");
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StoreCatalog> allCatalog = null;

        List<Cart> carts = null;
        String sumInCart = "";

        try {
            allCatalog = catalogBean.getAll();
        } catch (Exception e) {
            System.out.println("Product list exception");
            e.printStackTrace();
        }

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");//new Customer("m", "CE3FA5E93A4AB501:1000:CC98806DCC907191DE6A9B8075AF68FE64DD2E5C0E6CD83185028EDE4B5F347F",
             //   "lettercatcher@lis.ru", "m", "m", "master card");//

        carts = cartBean.getCart(customer);
        sumInCart = cartBean.getCartSumById(customer);

        //If out of stock, it is not displayed in the store
        for(int i = allCatalog.size()-1; i>=0; i--){
            if(allCatalog.get(i).getQuantity() == 0) allCatalog.remove(i);
        }

//        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));

        System.setProperty("upload", System.getProperty("jboss.server.data.dir"));
        String pathToSaveNewFile = req.getPathInfo();///System.getProperty("upload");

//        String s = allCatalog.toString();
        req.setAttribute("cata", allCatalog);
        req.setAttribute("cart", carts);
        req.setAttribute("cart_sum", sumInCart);
        req.setAttribute("UPLOAD", pathToSaveNewFile);
        System.out.println("Was in list products ++++++++++++++++++");
        // send to JSP page (view)
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/p2.jsp");
        req.getRequestDispatcher("/customer/p2.jsp").forward(req, resp);
//        try {
//            dispatcher.forward(req, resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");
        cartBean.addToCart(customer, req.getParameter("id"));
    }
}
