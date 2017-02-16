package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CustomerDao;
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

/**
 * Created by admin on 11.12.2016.
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("registr") != null){
            getServletContext().getRequestDispatcher("/registration-customer.jsp").forward(req, resp);
            return;
        }

        if (null == req.getParameter("login") || null == req.getParameter("password")
                || req.getParameter("login").isEmpty()
                || req.getParameter("password").isEmpty()) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Customer customer = null;

        try {
            CustomerDao customerDao =  new PostgreSqlCustomerDao(dataSource);
            customer = customerDao.read(login);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("myUser", customer);

        if(password.equals(customer.getPassword())) {
            //RequestDispatcher dispatcher = req.getRequestDispatcher("/customer-page.jsp");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/mts");
            dispatcher.forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
