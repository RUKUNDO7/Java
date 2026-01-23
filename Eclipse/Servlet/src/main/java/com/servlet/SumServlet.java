package com.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        int sum = num1 + num2;

        ServletConfig config = getServletConfig();
        String servletDescription=config.getInitParameter("ServletDescription");

        ServletContext context = getServletContext();
        String appName=context.getInitParameter("appName");


        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("Appname:"+appName);
        //out.println("Description:"+servletDescription);

        out.println("Sum:"+sum);
    }
}