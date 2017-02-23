package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.dao.POJO.storeCatalog;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Created by book on 28.12.2016.
 */
@WebServlet(urlPatterns = {"/customer/mts"
})
public class MyTestServlet extends HttpServlet {

    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;
    private CustomerDao customerDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            customerDao = new PostgreSqlCustomerDao(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        Customer customer;
        String username = request.getRemoteUser();

        if (username != null && request.getSession().getAttribute("myUser") == null) {
            // First-time login. You can do your thing here.
            customer = customerDao.read(username);
            request.getSession().setAttribute("myUser", customer);
        }

        //String userPath = request.getServletPath();

//        HttpURLConnection httpURLConnection;
//        URL url = new URL("http://localhost:8080/testMaven/restsrv/catalog");
//        httpURLConnection = (HttpURLConnection) url.openConnection();
//
//        PostgreCartDAO pCartDao = new PostgreCartDAO();
//
//        String string = "";
//        string = request.getParameter("command");
////        if (string == "ADD")
//            try {
//                pCartDao.AddToCart("7");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        System.out.println("INTO IF!!!");
//
//
//
//
//        List<storeCatalog> alCat = null;
//        PostgreCatalogDAO pcd = new PostgreCatalogDAO();
//        try {
//            alCat = pcd.getAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String s = alCat.toString();
//
//        List<Cart> getCart = null;
//        String sum_in_cart = "";
//
//        try {
//            getCart = pCartDao.readById(1);
//            sum_in_cart = pCartDao.getCartSumById(3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        for(Cart c : getCart) {
////            c.
//        }
//
//
//        RequestDispatcher rd = null;
//        rd = request.getRequestDispatcher("/p2.jsp");
//        request.setAttribute("cata", alCat);
//        request.setAttribute("cart", getCart);
//        request.setAttribute("cart_sum", sum_in_cart);
////        PrintWriter pw = response.getWriter();
////        pw.println("<H1>Hello, world!" + s +  "</H1>");
//        rd.forward(request, response);

        try {
            // read the hidden "command" parameter
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "PRODUCT_LIST";
            }

            // route to the appropriate method
            switch (theCommand) {
                case "PRODUCT_LIST":
                    listProducts(request, response);
                    break;
                case "ADD":
                    AddToCart(request, response);
                    break;
                default:
                    listProducts(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<storeCatalog> alCat = null;
        PostgreCatalogDAO pcd = new PostgreCatalogDAO();
        PostgreCartDAO pCartDao = new PostgreCartDAO();

        List<Cart> getCart = null;
        String sum_in_cart = "";

        alCat = pcd.getAll();

        getCart = pCartDao.readById(1);
        sum_in_cart = pCartDao.getCartSumById(3);

        String s = alCat.toString();
        request.setAttribute("cata", alCat);
        request.setAttribute("cart", getCart);
        request.setAttribute("cart_sum", sum_in_cart);
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/p2.jsp");
        dispatcher.forward(request, response);
    }


    private void AddToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostgreCartDAO pCartDao = new PostgreCartDAO();
        pCartDao.AddToCart(request.getParameter("id"));

        listProducts(request, response);
//        RequestDispatcher rd = null;
//        rd = request.getRequestDispatcher("/p2.jsp");
//        rd.forward(request, response);
    }

    private void getCart() {

    }
}
