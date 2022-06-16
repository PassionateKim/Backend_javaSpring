package spring.mvc.mvcpattern.controller3;

import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller3.controllers.FormController3;
import spring.mvc.mvcpattern.controller3.controllers.ListController3;
import spring.mvc.mvcpattern.controller3.controllers.SaveController3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController3", urlPatterns = "/mvc-pattern/controller3/*")
public class FrontController3 extends HttpServlet {
    Map<String, Controller3> controllerMap = new HashMap<>();

    public FrontController3() {
        controllerMap.put("/mvc-pattern/controller3/form", new FormController3());
        controllerMap.put("/mvc-pattern/controller3/save", new SaveController3());
        controllerMap.put("/mvc-pattern/controller3/list", new ListController3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request의 정보를 저장할 paramMap
        Map<String, String> paramMap = new HashMap<>();

        // paramMap에 request 요청 정보 넣기
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));

        // 알맞은 controller 꺼내기
        String requestURI = req.getRequestURI();
        Controller3 controller3 = controllerMap.get(requestURI);

        // controller 메서드 실행하기 -> viewPath정보와 paramMap를 가진 modelView
        ModelView modelView = controller3.logic(paramMap);

        //viewResolver: "new-form" -> "/WEB-INF/views/new-form.jsp"
        String viewName = modelView.getViewName();
        ViewRenderer viewRenderer = viewResolver(viewName);

        // view 이동
        viewRenderer.process(modelView.getModel(), req ,resp);

    }

    private ViewRenderer viewResolver(String viewName) {
        return new ViewRenderer("/WEB-INF/views/" + viewName + ".jsp");
    }
}
