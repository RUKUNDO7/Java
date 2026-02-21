package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import student.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentController extends HttpServlet {

    protected  void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Student st1=new Student("Melissa","Shami",17);
        Student st2=new Student("Sandrine","Utuje",19);
        List<Student> students=new ArrayList<>();
        students.add(st1);
        students.add(st2);
        req.setAttribute("students",students);
        req.getRequestDispatcher("display.jsp").forward(req,res);
    }
}
