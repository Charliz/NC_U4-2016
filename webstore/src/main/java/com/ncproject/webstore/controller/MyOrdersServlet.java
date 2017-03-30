package com.ncproject.webstore.controller;

import com.ncproject.webstore.ejb.OrderBeanInterface;
import com.ncproject.webstore.ejb.beans.EmailSessionBean;
import com.ncproject.webstore.entity.MailEvent;
import com.ncproject.webstore.entity.Orders;
import com.ncproject.webstore.ejb.CartBeanInterface;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.EJB;
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

    @EJB
    private CartBeanInterface cartBean;
    @EJB
    private OrderBeanInterface orderBean;
    @EJB
    private EmailSessionBean emailBean;

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
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        System.out.println("was in ORDERS ****************");

        orders = orderBean.readById(customer);
        System.out.println("was in ORDERS 2 ****************");
        String sumInCart = cartBean.getCartSumById(customer);

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
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");
        String emailTo = customer.getEmail();
        System.out.println("was in new order ++++++++++++++");
        try {
            orders = orderBean.readById(customer);
            List<Orders> orders1 = new ArrayList<Orders>();
            orders1.add(orders.get(orders.size()-1));
            orders = orders1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sumInCart = cartBean.getCartSumById(customer);
        System.out.println("was in new order ++++++++++++++ go over try");
        req.setAttribute("ords", orders);
        req.setAttribute("cart_sum", sumInCart);
        System.out.println("was in new order ++++++++++++++ go over set attr");
        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/order.jsp");
        sendEmail(emailTo);
        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        if (Double.parseDouble(cartBean.getCartSumById(customer)) > 0) {
            orderBean.createOrder(customer);
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

    private void sendEmail(String to) {
        MailEvent mailEvent = new MailEvent();
        mailEvent.set_To(to);
        mailEvent.setSubject("Order Confirmation");
        mailEvent.setMessage("Hello!) Your Order is confirmed! We'll ship it soon! Best regards from NC Shop =)");

        emailBean.SendOrderStatus(mailEvent); //firing mail event!
    }
}