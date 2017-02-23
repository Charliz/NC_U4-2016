package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.dao.CatalogDAO;
import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.dao.POJO.storeCatalog;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;
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
import java.util.List;

/**
 * Created by Черный on 22.02.2017.
 */
@WebServlet(urlPatterns = {"/customer/mts"})
public class ProductListForCustomerServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Add our customer to session
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        Customer customer = customerDao.read(req.getRemoteUser());
        HttpSession session = req.getSession();
        session.setAttribute("myUser", customer);

        // read the hidden "command" parameter
        String theCommand = req.getParameter("command");

        if("ADD".equals(theCommand)){
            AddToCart(req, resp);
            listProducts(req, resp);
        }else {
            listProducts(req, resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) {

        List<storeCatalog> allCatalog = null;
        CatalogDAO catalogDAO = new PostgreCatalogDAO();
        CartDAO cartDao = new PostgreCartDAO();

        List<Cart> carts = null;
        String sumInCart = "";

        try {
            allCatalog = catalogDAO.getAll();
        } catch (Exception e) {
            System.out.println("Product list exception");
            e.printStackTrace();
        }

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");

        carts = cartDao.readById(customer.getId());
        sumInCart = cartDao.getCartSumById(customer.getId());

        String s = allCatalog.toString();
        req.setAttribute("cata", allCatalog);
        req.setAttribute("cart", carts);
        req.setAttribute("cart_sum", sumInCart);

        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/p2.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddToCart(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("myUser");
        CartDAO cartDao = new PostgreCartDAO();
        cartDao.addToCart(customer.getId(), Integer.parseInt(req.getParameter("id")));
    }
}
