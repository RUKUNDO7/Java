package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FooterServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
        PrintWriter out=res.getWriter();
        out.println("<p>@2026 RCA </p>");
    }

}
