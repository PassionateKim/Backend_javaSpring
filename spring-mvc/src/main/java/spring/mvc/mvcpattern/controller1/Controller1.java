package spring.mvc.mvcpattern.controller1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller1 {
    void logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
