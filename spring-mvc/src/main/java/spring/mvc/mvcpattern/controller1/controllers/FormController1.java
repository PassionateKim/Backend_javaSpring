package spring.mvc.mvcpattern.controller1.controllers;

import spring.mvc.mvcpattern.controller1.Controller1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormController1 implements Controller1 {
    @Override
    public void logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FormController1.logic");
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
