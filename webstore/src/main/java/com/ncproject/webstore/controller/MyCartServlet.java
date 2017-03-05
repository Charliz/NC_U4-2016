package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CatalogDAO;
import com.ncproject.webstore.entity.CartWithNames;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;
import com.ncproject.webstore.ejb.CartBeanInterface;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Created by book on 28.12.2016.
 */
@WebServlet("/cart")
public class MyCartServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;
    @EJB
    private CartBeanInterface cartBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // read the hidden "command" parameter
        String theCommand = req.getParameter("command");

        if("DEL".equals(theCommand)){
            delFromCart(req, resp);
            listProducts(req, resp);
        }else {
            listProducts(req, resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp){
        List<CartWithNames> alCat = null;
        CatalogDAO catalogDAO = new PostgreCatalogDAO(dataSource);
        String sumInCart = "";

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        try {
            alCat = catalogDAO.getByCustomerId(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        sumInCart = cartBean.getCartSumById(customer, dataSource);

        req.setAttribute("cata", alCat);
        req.setAttribute("cart_sum", sumInCart);

        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delFromCart(HttpServletRequest req, HttpServletResponse resp){
        cartBean.delFromCart(Integer.parseInt(req.getParameter("id")), dataSource);
    }
}