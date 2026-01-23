package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet{

    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {

        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        int n1= Integer.parseInt(req.getParameter("n1"));
        int n2= Integer.parseInt(req.getParameter("n2"));
        String operations=req.getParameter("op");
        int result;

        if(operations.equals("add")) {
            result=n1+n2;
        }else {
            result=n1-n2;
        }

        RequestDispatcher header = req.getRequestDispatcher("/header");
        header.include(req, res);

        out.println("<main>");
        out.println("<h3>Result</h3>");
        out.println("<p>" + n1 + " " + (operations.equals("add") ? "+" : "-") + " " + n2 + " = <b>" + result + "</b></p>");
        out.println("<a href='main'>Go Back</a>");
        out.println("</main>");

        RequestDispatcher footer = req.getRequestDispatcher("/footer");
        footer.include(req, res);
    }

}

