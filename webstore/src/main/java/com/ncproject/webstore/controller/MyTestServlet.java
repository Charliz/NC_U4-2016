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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Created by book on 28.12.2016.
 */
//@WebServlet(urlPatterns = {"/customer/mts"})
public class MyTestServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        Customer customer = null;

        try {
            CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
            customer = customerDao.read(request.getRemoteUser());
        } catch (Exception e) {
        }

        HttpSession session = request.getSession();
        session.setAttribute("myUser", customer);

        /*HttpSession session = request.getSession();
        session.setAttribute("myUser", customer);*/

        //String userPath = request.getServletPath();

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

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        getCart = pCartDao.readById(customer.getId());
        sum_in_cart = pCartDao.getCartSumById(customer.getId());

        String s = alCat.toString();
        request.setAttribute("cata", alCat);
        request.setAttribute("cart", getCart);
        request.setAttribute("cart_sum", sum_in_cart);
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/p2.jsp");
        dispatcher.forward(request, response);
    }


    private void AddToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");
        PostgreCartDAO pCartDao = new PostgreCartDAO();
        pCartDao.addToCart(customer.getId(), Integer.parseInt(request.getParameter("id")));

        listProducts(request, response);
    }

    private void getCart() {

    }
}
