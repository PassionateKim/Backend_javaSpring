package spring.mvc.mvcpattern.controller3.controllers;

import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.controller1.Controller1;
import spring.mvc.mvcpattern.controller3.Controller3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FormController3 implements Controller3 {
    @Override
    public ModelView logic(Map<String, String> paramMap){
        System.out.println("FormController3.logic");
        String viewPath = "new-form";
        ModelView modelView = new ModelView(viewPath);
        return modelView;
    }
}
