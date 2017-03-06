package com.ncproject.webstore.controller;

import com.ncproject.webstore.ejb.CustomerBeanInterface;
import com.ncproject.webstore.ejb.OrderBeanInterface;
import com.ncproject.webstore.ejb.ProductBeanInterface;
import com.ncproject.webstore.entity.Customer;
import com.ncproject.webstore.entity.Orders;

import com.ncproject.webstore.entity.Product;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/",
        "/admin/searchProducts",
        "/admin/listProducts",
        "/admin/loadProductToForm",
        "/admin/createProduct",
        "/admin/updateProduct",
        "/admin/deleteProduct",
        "/admin/customerManager",
        "/admin/listOrders",
        "/admin/loadEmailForm",
        "/admin/deleteCustomer",
        "/admin/uploadPicture"
        })
@MultipartConfig
public class ControllerServlet extends HttpServlet {
    @EJB
    private CustomerBeanInterface customerBean;
    @EJB
    private ProductBeanInterface productBean;
    @EJB
    private OrderBeanInterface orderBean;

    private static final long serialVersionUID = 1L;
    private String pathToSaveNewFile;

    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;


    @Override
    public void init() throws ServletException {
        super.init();
        System.setProperty("upload", System.getProperty("jboss.server.data.dir"));
        pathToSaveNewFile = System.getProperty("upload");
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
            request.setAttribute("UPLOAD", pathToSaveNewFile);

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
            orders = orderBean.getAllOrders(dataSource);

            // add orders to the request
            request.setAttribute("ORDERS_LIST", orders);

            userPath = "/list-orders";
        } else if (userPath.equals("/admin/loadEmailForm")) {
            // read customer id parameter from the "Update" link
            String idString = request.getParameter("customerId");
            int id = Integer.parseInt(idString);

            customer = customerBean.readById(id, dataSource);
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

        //part for upload picture ========================================================

        else if (userPath.equals("/admin/uploadPicture")) {
//            Product theProduct = new Product();
//            theProduct.setDescription(request.getParameter("description"));
//            theProduct.setProductName(request.getParameter("productName"));
//            theProduct.setPrice(new BigDecimal(request.getParameter("price")));
//            theProduct.setBrand(request.getParameter("brand"));
//
//            productBean.createProduct(theProduct, dataSource);

            String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
            System.setProperty("upload", System.getProperty("jboss.server.data.dir"));
            pathToSaveNewFile = System.getProperty("upload"); //System.getProperty("jboss.server.data.dir");
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            File f = new File(pathToSaveNewFile + "\\" + description + ".jpg");

            Files.copy(fileContent, f.toPath(), StandardCopyOption.REPLACE_EXISTING);

            response.sendRedirect("/webstore/admin/listProducts");
        }

    }

}