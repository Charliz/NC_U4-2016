package com.ncproject.webstore.controller;

import com.ncproject.webstore.ejb.CustomerBeanInterface;
import com.ncproject.webstore.ejb.ProductBeanInterface;
import com.ncproject.webstore.entity.Customer;
import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.entity.Orders;
import com.ncproject.webstore.dao.ProductDao;
import com.ncproject.webstore.dao.postgreSql.PostgreOrdersDAO;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlProductDao;
import com.ncproject.webstore.entity.Customer;
import com.ncproject.webstore.entity.Product;

import javax.annotation.Resource;
import javax.ejb.EJB;
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
        "/admin/listOrders",
        "/admin/loadEmailForm",
        })

public class ControllerServlet extends HttpServlet {
    @EJB
    private CustomerBeanInterface customerBean;
    @EJB
    private ProductBeanInterface productBean;

    private static final long serialVersionUID = 1L;

    private ProductDao postgreSqlProductDao;
    private OrdersDAO postgreOrdersDAO;
    private CustomerDao postgreSqlCustomerDao;

    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            postgreSqlProductDao = new PostgreSqlProductDao(dataSource);
            postgreOrdersDAO = new PostgreOrdersDAO(dataSource);
            postgreSqlCustomerDao = new PostgreSqlCustomerDao(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        List<Product> products;
        List<Orders> orders;
        Customer customer;
        List<Customer> customers;

        if (userPath.equals("/admin/listProducts")) {

            // get products from the PostgreSqlProductDao
            products = productBean.getAllProducts(dataSource);

            // add products to the request
            request.setAttribute("PRODUCT_LIST", products);

            userPath = "/list-products";

        } else if (userPath.equals("/admin/searchProducts")) {
                String nameString = request.getParameter("productName");

                if (nameString != null && !nameString.trim().isEmpty()) {
                    products = productBean.searchProducts(nameString, dataSource);
                    request.setAttribute("PRODUCTS", products);
                }
                userPath = "/search-products";
                //response.sendRedirect("/webstore/admin/listProducts");

        } else if (userPath.equals("/admin/loadProductToForm")) {
            // read product id parameter from the "Update" link
            String idString = request.getParameter("productId");
            int id = Integer.parseInt(idString);

            Product theProduct = productBean.getProductById(id, dataSource);

            // place product object in the request attribute so the JSP can use it to pre-populate the form
            request.setAttribute("THE_PRODUCT", theProduct);

            userPath = "/update-product-form";

        } else if (userPath.equals("/admin/deleteProduct")) {
            // read product id parameter from the "Delete" link
            String idString = request.getParameter("productId");
            int id = Integer.parseInt(idString);

            // delete product from the DB
            productBean.deleteProduct(id, dataSource);
            response.sendRedirect("/webstore/admin/listProducts");

        } else if (userPath.equals("/admin/listOrders")) {
            orders = postgreOrdersDAO.getAllOrders();

            // add orders to the request
            request.setAttribute("ORDERS_LIST", orders);

            userPath = "/list-orders";
        } else if (userPath.equals("/admin/loadEmailForm")) {
            // read customer id parameter from the "Update" link
            String idString = request.getParameter("customerId");
            int id = Integer.parseInt(idString);

            customer = postgreSqlCustomerDao.readById(id);
            request.setAttribute("THE_CUSTOMER", customer);

            userPath = "/status-update";

        }

        //here my task =====================================================
        else if (userPath.equals("/admin/customerManager")) {
            customers = customerBean.getAll(dataSource);

            // add products to the request
            request.setAttribute("users", customers);
            userPath = "/customer-manager";
        }
        else if (userPath.equals("/admin/deleteCustomer")) {
            String idEmail = request.getParameter("custEmail");
//            Customer customerToDel = pscd.getByEmail(idEmail);
            customerBean.delete(idEmail, dataSource);

            customers = customerBean.getAll(dataSource);
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
            theProduct.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            theProduct.setBrand(request.getParameter("brand"));

            productBean.updateProduct(theProduct, dataSource);

            response.sendRedirect("/webstore/admin/listProducts");

        } else if (userPath.equals("/admin/createProduct")) {
            Product theProduct = new Product();
            theProduct.setDescription(request.getParameter("description"));
            theProduct.setProductName(request.getParameter("productName"));
            theProduct.setPrice(new BigDecimal(request.getParameter("price")));
            theProduct.setBrand(request.getParameter("brand"));
            theProduct.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            productBean.createProduct(theProduct, dataSource);

            response.sendRedirect("/webstore/admin/listProducts");
        }

    }

}