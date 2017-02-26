package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CatalogDAO;
import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.dao.POJO.StoreCatalog;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.ejb.CartBeanInterface;
import com.ncproject.webstore.entity.Customer;

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
import java.io.IOException;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Add our customer to session
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        Customer customer = customerDao.read(req.getRemoteUser());
        HttpSession session = req.getSession();
        session.setAttribute("myUser", customer);

        // read the hidden "command" parameter
        String theCommand = req.getParameter("command");

        if("ADD".equals(theCommand)){
            addToCart(req, resp);
            listProducts(req, resp);
        }else {
            listProducts(req, resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) {
        List<StoreCatalog> allCatalog = null;
        CatalogDAO catalogDAO = new PostgreCatalogDAO(dataSource);

        List<Cart> carts = null;
        String sumInCart = "";

        try {
            allCatalog = catalogDAO.getAll();
        } catch (Exception e) {
            System.out.println("Product list exception");
            e.printStackTrace();
        }

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        carts = cartBean.getCart(customer, dataSource);
        sumInCart = cartBean.getCartSumById(customer, dataSource);

        //If out of stock, it is not displayed in the store
        for(int i = allCatalog.size()-1; i>=0; i--){
            if(allCatalog.get(i).getQuantity() == 0) allCatalog.remove(i);
        }

        String s = allCatalog.toString();
        req.setAttribute("cata", allCatalog);
        req.setAttribute("cart", carts);
        req.setAttribute("cart_sum", sumInCart);

        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/p2.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");
        cartBean.addToCart(customer, req.getParameter("id"), dataSource);
    }
}
