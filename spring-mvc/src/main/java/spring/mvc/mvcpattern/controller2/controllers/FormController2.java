package spring.mvc.mvcpattern.controller2.controllers;

import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller2.Controller2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormController2 implements Controller2 {
    @Override
    public ViewRenderer logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FormController2.logic");
        String viewPath = "/WEB-INF/views/new-form.jsp";
        ViewRenderer viewRenderer = new ViewRenderer(viewPath);
        return viewRenderer;
    }
}
