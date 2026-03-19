package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
    @Override 
    public void init() throws ServletException {
        System.out.println("Servlet initialized...");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Set response content type
        response.setContentType("text/html");

        //Get data from request
        String name = request.getParameter("name");

        //Write response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Hello " + (name != null ? name : "Guest") + " </h1>");
        out.println("</body></html>");
    }

        @Override
        public void destroy() {
            System.out.println("Servlet destroyed...");
        }

    }

