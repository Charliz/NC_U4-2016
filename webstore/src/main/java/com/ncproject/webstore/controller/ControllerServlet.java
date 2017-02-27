package com.ncproject.webstore.controller;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.ProductDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlProductDao;
import com.ncproject.webstore.entity.Customer;
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

@WebServlet(urlPatterns = {"/admin/",
        "/admin/searchProducts",
        "/admin/listProducts",
        "/admin/loadProductToForm",
        "/admin/createProduct",
        "/admin/updateProduct",
        "/admin/deleteProduct",
        "/admin/customerManager",
        "/admin/deleteCustomer"
        })

public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDao postgreSqlProductDao;
    private CustomerDao pscd;

    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            postgreSqlProductDao = new PostgreSqlProductDao(dataSource);
            pscd = new PostgreSqlCustomerDao(dataSource);
//            pscd.setDataSource(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String userPath = request.getServletPath();
        List<Product> products;
        List<Customer> customers;

        if (userPath.equals("/admin/listProducts")) {

            // get products from the PostgreSqlProductDao
            products = postgreSqlProductDao.getAllProducts();

            // add products to the request
            request.setAttribute("PRODUCT_LIST", products);

            userPath = "/list-products";

        } else if (userPath.equals("/admin/searchProducts")) {
                String nameString = request.getParameter("productName");

                if (nameString != null && !nameString.trim().isEmpty()) {
                    products = postgreSqlProductDao.searchProducts(nameString);
                    request.setAttribute("PRODUCTS", products);

                }
                userPath = "/search-products";
                //response.sendRedirect("/webstore/admin/listProducts");


        } else if (userPath.equals("/admin/loadProductToForm")) {
            // read product id parameter from the "Update" link
            String idString = request.getParameter("productId");
            int id = Integer.parseInt(idString);

            Product theProduct = postgreSqlProductDao.getProductById(id);

            // place product object in the request attribute so the JSP can use it to pre-populate the form
            request.setAttribute("THE_PRODUCT", theProduct);

            userPath = "/update-product-form";

        } else if (userPath.equals("/admin/deleteProduct")) {
            // read product id parameter from the "Delete" link
            String idString = request.getParameter("productId");
            int id = Integer.parseInt(idString);

            // delete product from the DB
            postgreSqlProductDao.deleteProduct(id);
            response.sendRedirect("/webstore/admin/listProducts");

        }

        //here my task =====================================================
        else if (userPath.equals("/admin/customerManager")) {
            customers = pscd.getAll();

            // add products to the request
            request.setAttribute("users", customers);
            userPath = "/customer-manager";
        }
        else if (userPath.equals("/admin/deleteCustomer")) {
            String idEmail = request.getParameter("custEmail");
//            Customer customerToDel = pscd.getByEmail(idEmail);
            pscd.delete(idEmail);

            customers = pscd.getAll();
            request.setAttribute("users", customers);
            userPath = "/customer-manager";
        }


        // use RequestDispatcher to forward request internally
        String url = "/admin" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/admin/updateProduct")) {
            Product theProduct = new Product();
            theProduct.setProd_id(Integer.parseInt(request.getParameter("productId")));
            theProduct.setDescription(request.getParameter("description"));
            theProduct.setProductName(request.getParameter("productName"));
            theProduct.setPrice(new BigDecimal(request.getParameter("price")));
            theProduct.setBrand(request.getParameter("brand"));

            postgreSqlProductDao.updateProduct(theProduct);

            response.sendRedirect("/webstore/admin/listProducts");

        } else if (userPath.equals("/admin/createProduct")) {
            Product theProduct = new Product();
            theProduct.setDescription(request.getParameter("description"));
            theProduct.setProductName(request.getParameter("productName"));
            theProduct.setPrice(new BigDecimal(request.getParameter("price")));
            theProduct.setBrand(request.getParameter("brand"));

            postgreSqlProductDao.createProduct(theProduct);

            response.sendRedirect("/webstore/admin/listProducts");
        }

    }

}