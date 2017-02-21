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
@WebServlet(urlPatterns = {"/customer/customerPage/"

        })
public class CustomerServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Customer customer = (Customer) req.getSession(true).getAttribute("login");

            try {
                CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
                customer = customerDao.read(req.getRemoteUser());
            } catch (Exception e) {
            }


        HttpSession session = req.getSession();
        session.setAttribute("myUser", customer);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/customer-page.jsp");
        dispatcher.forward(req, resp);

    }
}
