package spring.mvc.mvcpattern.controller2;

import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller1.Controller1;
import spring.mvc.mvcpattern.controller1.controllers.FormController1;
import spring.mvc.mvcpattern.controller1.controllers.ListController1;
import spring.mvc.mvcpattern.controller1.controllers.SaveController1;
import spring.mvc.mvcpattern.controller2.controllers.FormController2;
import spring.mvc.mvcpattern.controller2.controllers.ListController2;
import spring.mvc.mvcpattern.controller2.controllers.SaveController2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController2", urlPatterns = "/mvc-pattern/controller2/*")
public class FrontController2 extends HttpServlet {
    Map<String, Controller2> controllerMap = new HashMap<>();

    public FrontController2() {
        controllerMap.put("/mvc-pattern/controller2/form", new FormController2());
        controllerMap.put("/mvc-pattern/controller2/save", new SaveController2());
        controllerMap.put("/mvc-pattern/controller2/list", new ListController2());
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontController2.service");
        String requestURI = req.getRequestURI();
        Controller2 controller2 = controllerMap.get(requestURI);

        //controller2의 url이 담긴 viewRenderer
        ViewRenderer viewRenderer = controller2.logic(req, resp);
        //view 이동
        viewRenderer.process(req, resp);
    }
}
