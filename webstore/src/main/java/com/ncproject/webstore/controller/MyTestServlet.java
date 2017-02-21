package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.dao.POJO.storeCatalog;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;

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
@WebServlet("/mts")
public class MyTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");


        try {
            // read the hidden "command" parameter
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "PRODUCT_LIST";
            }

            // route to the appropriate method
            switch (theCommand) {
                case "PRODUCT_LIST":
                    listProducts(request, response);
                    break;
                case "ADD":
                    AddToCart(request, response);
                    break;
                default:
                    listProducts(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<storeCatalog> alCat = null;
        PostgreCatalogDAO pcd = new PostgreCatalogDAO();
        PostgreCartDAO pCartDao = new PostgreCartDAO();

        List<Cart> getCart = null;
        String sum_in_cart = "";

        alCat = pcd.getAll();

        getCart = pCartDao.readById(1);
        sum_in_cart = pCartDao.getCartSumById(3);

        String s = alCat.toString();
        request.setAttribute("cata", alCat);
        request.setAttribute("cart", getCart);
        request.setAttribute("cart_sum", sum_in_cart);
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/p2.jsp");
        dispatcher.forward(request, response);
    }


    private void AddToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostgreCartDAO pCartDao = new PostgreCartDAO();
        pCartDao.AddToCart(request.getParameter("id"));

        listProducts(request, response);
    }

    private void getCart() {

    }
}
