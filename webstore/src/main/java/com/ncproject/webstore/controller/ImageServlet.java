package com.ncproject.webstore.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by secret on 06.03.2017.
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.setProperty("upload", System.getProperty("jboss.server.data.dir"));
        String pathToSaveNewFile = System.getProperty("upload");
//        File f = new File(pathToSaveNewFile + "\\"  + "wer.jpg");
//        response.setContentType("image/jpeg");
        String fileName = request.getPathInfo();
        String pathToWeb = System.getProperty("upload");//getServletContext().getRealPath(File.separator);
        File f = new File(pathToWeb + "\\"  + fileName);
        BufferedImage bi = ImageIO.read(f);
        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();

    }

}
