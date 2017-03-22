package com.ncproject.webstore.controller;

import com.ncproject.webstore.ejb.CustomerBeanInterface;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

import static com.ncproject.webstore.utils.EncryptionUtil.hash;

/**
 * Created by Черный on 28.12.2016.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;
    @EJB
    private CustomerBeanInterface customerBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String payment = req.getParameter("payment");

        if (null == login || null == password || null == email || null == name || null == address
                || login.isEmpty() || password.isEmpty() || email.isEmpty()
                || name.isEmpty() || address.isEmpty() || payment.isEmpty()
                || !password.equals(confirmPassword)) {
            getServletContext().getRequestDispatcher("/registration-customer.jsp").forward(req, resp);
            return;
        }

        String hashedPassword = hash(password, null);

        Customer customer = new Customer(login, hashedPassword, email, name, address, payment);

        try {
            customerBean.create(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("myUser", customer);
        req.login(login, password);

        resp.sendRedirect("/webstore/customer/registrationSuccess.jsp");
        // getServletContext().getRequestDispatcher("/customer/registrationSuccess.jsp").forward(req, resp);
    }

}