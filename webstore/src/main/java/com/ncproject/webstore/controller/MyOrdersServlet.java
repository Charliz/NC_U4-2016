package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.POJO.Orders;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreOrdersDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by book on 28.12.2016.
 */
@WebServlet("/myorders")
public class MyOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        try {
            // read the hidden "command" parameter
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "ORDER_LIST";
            }

            // route to the appropriate method
            switch (theCommand) {
                case "ORDER_LIST":
                    listOrders(request, response);
                    break;
                case "CORD":
                    CreateOrder(request, response);
                    break;
                default:
                    listOrders(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Orders> alCat = null;
        PostgreOrdersDAO pod = new PostgreOrdersDAO();
        PostgreCartDAO pCartDao = new PostgreCartDAO();
//
//        List<Cart> getCart = null;
        String sum_in_cart = "";

        alCat = pod.readById(3);

//        getCart = pCartDao.readById(1);
        sum_in_cart = pCartDao.getCartSumById(3);

//        String s = alCat.toString();
        request.setAttribute("ords", alCat);
//        request.setAttribute("cart", getCart);
        request.setAttribute("cart_sum", sum_in_cart);
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order.jsp");
        dispatcher.forward(request, response);
    }

    private void CreateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostgreOrdersDAO pOrdersDAO = new PostgreOrdersDAO();
        pOrdersDAO.createOrder(Integer.parseInt(request.getParameter("customer_id")));

        listOrders(request, response);
//        RequestDispatcher rd = null;
//        rd = request.getRequestDispatcher("/p2.jsp");
//        rd.forward(request, response);
    }
}