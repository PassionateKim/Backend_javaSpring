package spring.mvc.mvcpattern.controller4;

import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller4.controllers.FormController4;
import spring.mvc.mvcpattern.controller4.controllers.ListController4;
import spring.mvc.mvcpattern.controller4.controllers.SaveController4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController4", urlPatterns = "/mvc-pattern/controller4/*")
public class FrontController4 extends HttpServlet {
    Map<String, Controller4> controllerMap = new HashMap<>();

    public FrontController4() {
        controllerMap.put("/mvc-pattern/controller4/form", new FormController4());
        controllerMap.put("/mvc-pattern/controller4/save", new SaveController4());
        controllerMap.put("/mvc-pattern/controller4/list", new ListController4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request의 정보를 저장할 paramMap
        Map<String, String> paramMap = new HashMap<>();
        Map<String, Object> model = new HashMap<>();

        // paramMap에 request 요청 정보 넣기
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));

        // 알맞은 controller 꺼내기
        String requestURI = req.getRequestURI();
        Controller4 controller4 = controllerMap.get(requestURI);


        String viewName = controller4.logic(paramMap, model);
        ViewRenderer viewRenderer = viewResolver(viewName);

        // view 이동
        viewRenderer.process(model, req ,resp);

    }

    private ViewRenderer viewResolver(String viewName) {
        return new ViewRenderer("/WEB-INF/views/" + viewName + ".jsp");
    }
}
