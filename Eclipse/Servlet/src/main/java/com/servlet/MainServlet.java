package com.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        RequestDispatcher header = req.getRequestDispatcher("/header");
        header.include(req, res);

        out.println("<main>");
        out.println("<form action='calculate' method='post'>");

        out.println("Number 1: <input type='number' name='n1' required><br><br>");
        out.println("Number 2: <input type='number' name='n2' required><br><br>");

        out.println("<input type='radio' name='op' value='add' checked> Add ");
        out.println("<input type='radio' name='op' value='sub'> Subtract<br><br>");

        out.println("<input type='submit' value='Calculate'>");
        out.println("</form>");
        out.println("</main>");

        RequestDispatcher footer = req.getRequestDispatcher("/footer");
        footer.include(req, res);
    }
}

