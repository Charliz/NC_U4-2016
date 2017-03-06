package com.ncproject.webstore.controller;

import com.ncproject.webstore.ejb.beans.EmailSessionBean;
import com.ncproject.webstore.ejb.beans.TopicSessionBean;
import com.ncproject.webstore.entity.MailEvent;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Champion on 27.02.2017.
 */
@WebServlet("/emailServlet")
public class EmailServlet extends HttpServlet {

    @EJB
    private TopicSessionBean topicSessionBean;

    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MailEvent mailEvent = new MailEvent();
        String email = request.getParameter("to");
        mailEvent.set_To(email);
        mailEvent.setSubject(request.getParameter("subject"));
        mailEvent.setMessage(request.getParameter("body"));

        topicSessionBean.SendOrderStatus(mailEvent);
        request.setAttribute("email", email);
        getServletContext().getRequestDispatcher("/admin/emailSuccess.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
