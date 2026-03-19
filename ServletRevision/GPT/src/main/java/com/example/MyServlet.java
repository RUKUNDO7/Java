package com.example;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class MyServlet extends HttpServlet {

    // 1. INIT METHOD (called once)
    public void init() throws ServletException {
        System.out.println("Servlet initialized...");
    }

    // 2. SERVICE METHOD (called for every request)
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>Hello, this is service method</h1>");
    }

    // 3. DESTROY METHOD (called once)
    public void destroy() {
        System.out.println("Servlet destroyed...");
    }
}

/*
What happens when this runs (using Apache Tomcat):
1. Servlet is loaded
2. init() → runs once
3. For each request → service() runs
4. When server stops → destroy() runs
*/