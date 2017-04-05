package com.ncproject.webstore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session= req.getSession();
        session.removeAttribute("myUser");
        session.removeAttribute("myAdmin");
        session.invalidate();
        RequestDispatcher rd = req.getRequestDispatcher("/userIndex.jsp"); //The url where go after logout
        rd.forward(req,res);
    }
}
