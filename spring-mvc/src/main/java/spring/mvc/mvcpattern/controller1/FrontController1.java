package spring.mvc.mvcpattern.controller1;

import spring.mvc.mvcpattern.controller1.controllers.FormController1;
import spring.mvc.mvcpattern.controller1.controllers.ListController1;
import spring.mvc.mvcpattern.controller1.controllers.SaveController1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController1", urlPatterns = "/mvc-pattern/controller1/*")
public class FrontController1 extends HttpServlet {
    Map<String, Controller1> controllerMap = new HashMap<>();

    public FrontController1() {
        controllerMap.put("/mvc-pattern/controller1/form", new FormController1());
        controllerMap.put("/mvc-pattern/controller1/save", new SaveController1());
        controllerMap.put("/mvc-pattern/controller1/list", new ListController1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        Controller1 controller1 = controllerMap.get(requestURI);

        controller1.logic(req, resp);
    }
}
