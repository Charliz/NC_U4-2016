package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Черный on 29.12.2016.
 */
@WebServlet("/customer/editUser")
public class EditUserServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/customer/edit-user.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        if (null == login || null == email || null == name || null == address
                || login.isEmpty() || email.isEmpty()
                || name.isEmpty() || address.isEmpty()){
            getServletContext().getRequestDispatcher("/customer/edit-user.jsp").forward(req, resp);
            return;
        }

        Customer customer = (Customer) req.getSession().getAttribute("myUser");

        if(login != customer.getLogin()) customer.setLogin(login);
        if(email != customer.getEmail()) customer.setEmail(email);
        if(name != customer.getName()) customer.setName(name);
        if(address != customer.getAddress()) customer.setAddress(address);

        try {
            CustomerDao customerDao =  new PostgreSqlCustomerDao(dataSource);
            customerDao.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        getServletContext().getRequestDispatcher("/customer/customer-page.jsp").forward(req, resp);
    }
}
