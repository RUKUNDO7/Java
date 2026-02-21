package com.studentapp.servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;

public class AddStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

        Student student = new Student(name, email, age);
        StudentDAO dao = new StudentDAO();
        dao.saveStudent(student);

        resp.getWriter().println("Student Saved Successfully!");
    }
}