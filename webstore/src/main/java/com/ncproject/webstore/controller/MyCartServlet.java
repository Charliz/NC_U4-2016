package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.POJO.CartWithNames;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;
import com.ncproject.webstore.entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by book on 28.12.2016.
 */
@WebServlet("/cart")
public class MyCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read the hidden "command" parameter
        String theCommand = req.getParameter("command");

        if("DEL".equals(theCommand)){
            DelFromCart(req, resp);
        }else if("CREATE_ORDER".equals(theCommand)){
            CreateOrder(req, resp);
        }else {
            listProducts(req, resp);
        }


    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp){

        List<CartWithNames> alCat = null;
        PostgreCatalogDAO pcd = new PostgreCatalogDAO();
        PostgreCartDAO pCartDao = new PostgreCartDAO();
        String sum_in_cart = "";

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        try {
            alCat = pcd.getByCustomerId(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        sum_in_cart = pCartDao.getCartSumById(customer.getId());

        String s = alCat.toString();
        req.setAttribute("cata", alCat);
        req.setAttribute("cart_sum", sum_in_cart);

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

    private void DelFromCart(HttpServletRequest request, HttpServletResponse response){
        PostgreCartDAO pCartDao = new PostgreCartDAO();
        pCartDao.delFromCart(Integer.parseInt(request.getParameter("id")));

        listProducts(request, response);
    }

    public void CreateOrder(HttpServletRequest request, HttpServletResponse response){

    }
}