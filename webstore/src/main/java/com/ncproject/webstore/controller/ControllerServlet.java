package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.PostgreSql.PostgreSqlProductDao;
import com.ncproject.webstore.entity.Product;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PostgreSqlProductDao postgreSqlProductDao;

    @Resource(lookup = "java:/PostgresNC")
    private DataSource dataSource;


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            postgreSqlProductDao = new PostgreSqlProductDao(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                    addOrUpdateProduct(request, response);
                    break;
                case "LOAD":
                    loadProductToForm(request, response);
                    break;
                case "UPDATE":
                    addOrUpdateProduct(request, response);
                    break;
                case "DELETE":
                    deleteProduct(request, response);
                    break;
                default:
                    listProducts(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get products from the PostgreSqlProductDao
        List<Product> products = postgreSqlProductDao.getAllProducts();

        // add products to the request
        request.setAttribute("PRODUCT_LIST", products);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-products.jsp");
        dispatcher.forward(request, response);
    }

    private void addOrUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idString = request.getParameter("productId");

        // read product info from the form
        String description = request.getParameter("description");
        String productName = request.getParameter("productName");
        String price = request.getParameter("price");
        BigDecimal priceBigDecimal = new BigDecimal(price);
        String brand = request.getParameter("brand");
        Product theProduct;

        try {
            int id = Integer.parseInt(idString.trim());
            if (idString != null && id > 0) {
                // create a new product object
                theProduct = new Product(id, description, productName, priceBigDecimal, brand);
                postgreSqlProductDao.updateProduct(theProduct);
            }
        }
        catch(NumberFormatException | NullPointerException nex) {
            theProduct = new Product(description, productName, priceBigDecimal, brand);
            postgreSqlProductDao.createProduct(theProduct);
        }

        // send back to the main page (Product list)
        listProducts(request, response);
    }

    private void loadProductToForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read product id parameter from the "Update" link
        String idString = request.getParameter("productId");

        Product theProduct = postgreSqlProductDao.getProductById(idString);

        // place product object in the request attribute so the JSP can use it to pre-populate the form
        request.setAttribute("THE_PRODUCT", theProduct);

        // send to JSP page: update-product-form.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read product id parameter from the "Delete" link
        String idString = request.getParameter("productId");

        // delete product from the DB
        postgreSqlProductDao.deleteProduct(idString);

        // send back to the main page (Product list)
        listProducts(request, response);

    }

}
