package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.dao.POJO.Orders;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreOrdersDAO;
import com.ncproject.webstore.entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by book on 28.12.2016.
 */
@WebServlet("/myorders")
public class MyOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // read the hidden "command" parameter
        String theCommand = req.getParameter("command");

        if("CORD".equals(theCommand)){
            createOrder(req, resp);
        }else {
            listOrders(req, resp);
        }
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp){
        List<Orders> orders = null;
        OrdersDAO ordersDAO = new PostgreOrdersDAO();
        CartDAO cartDAO = new PostgreCartDAO();

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        try {
            orders = ordersDAO.readById(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sumInCart = cartDAO.getCartSumById(customer.getId());

        req.setAttribute("ords", orders);
        req.setAttribute("cart_sum", sumInCart);

        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/orders.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newOrder(HttpServletRequest req, HttpServletResponse resp){
        List<Orders> orders = null;
        OrdersDAO ordersDAO = new PostgreOrdersDAO();
        CartDAO pCartDao = new PostgreCartDAO();

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        try {
            orders = ordersDAO.readById(customer.getId());
            List<Orders> orders1 = new ArrayList<Orders>();
            orders1.add(orders.get(orders.size()-1));
            orders = orders1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sumInCart = pCartDao.getCartSumById(customer.getId());

        req.setAttribute("ords", orders);
        req.setAttribute("cart_sum", sumInCart);

        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/order.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");
        CartDAO cartDAO = new PostgreCartDAO();

        if (Double.parseDouble(cartDAO.getCartSumById(customer.getId())) > 0) {
            OrdersDAO ordersDAO = new PostgreOrdersDAO();
            try {
                ordersDAO.createOrder(customer.getId());
            } catch (Exception e) {}
            newOrder(req, resp);
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/cart.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}