package spring.mvc.mvcpattern.controller4.controllers;

import spring.mvc.mvcpattern.controller4.Controller4;

import java.util.Map;

public class FormController4 implements Controller4 {
    @Override
    public String logic(Map<String, String> paramMap, Map<String, Object> model){
        System.out.println("FormController4.logic");
        String viewPath = "new-form";
        return viewPath;
    }
}
