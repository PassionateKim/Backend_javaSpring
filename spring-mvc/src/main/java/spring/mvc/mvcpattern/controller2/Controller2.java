package spring.mvc.mvcpattern.controller2;

import spring.mvc.mvcpattern.ViewRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller2 {
    public ViewRenderer logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
