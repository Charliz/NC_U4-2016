package com.ncproject.webstore.controller;

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
            listOrders(req, resp);
        }else {
            listOrders(req, resp);
        }
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp){
        List<Orders> alCat = null;
        PostgreOrdersDAO pod = new PostgreOrdersDAO();
        PostgreCartDAO pCartDao = new PostgreCartDAO();

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        try {
            alCat = pod.readById(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        getCart = pCartDao.readById(1);
        String sumInCart = pCartDao.getCartSumById(customer.getId());

//        String s = alCat.toString();
        req.setAttribute("ords", alCat);
        //request.setAttribute("cart", getCart);
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

        OrdersDAO pOrdersDAO = new PostgreOrdersDAO();
        try {
            pOrdersDAO.createOrder(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}