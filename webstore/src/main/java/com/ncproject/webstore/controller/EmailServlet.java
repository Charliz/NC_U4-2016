package com.ncproject.webstore.controller;

import com.ncproject.webstore.ejb.beans.EmailSessionBean;
import com.ncproject.webstore.entity.MailEvent;

import javax.ejb.EJB;
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
    private EmailSessionBean emailBean;

    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MailEvent mailEvent = new MailEvent();
        mailEvent.set_To(request.getParameter("to"));
        mailEvent.setSubject(request.getParameter("subject"));
        mailEvent.setMessage(request.getParameter("body"));

        emailBean.SendOrderStatus(mailEvent);
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