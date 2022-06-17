package spring.mvc.mvcpattern.controller5;

import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller3.controllers.FormController3;
import spring.mvc.mvcpattern.controller3.controllers.ListController3;
import spring.mvc.mvcpattern.controller3.controllers.SaveController3;
import spring.mvc.mvcpattern.controller4.controllers.FormController4;
import spring.mvc.mvcpattern.controller4.controllers.ListController4;
import spring.mvc.mvcpattern.controller4.controllers.SaveController4;
import spring.mvc.mvcpattern.controller5.adapters.HandlerAdapter3;
import spring.mvc.mvcpattern.controller5.adapters.HandlerAdapter4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontController5", urlPatterns = "/mvc-pattern/controller5/*")
public class FrontController5 extends HttpServlet {

    // 여러가지를 모두 담아야 하므로 Object로 넣어준다.
    Map<String, Object> controllerMap = new HashMap<>();
    // 어댑터
    List<HandlerAdapter> adapters = new ArrayList<>();
    public FrontController5() {
        // 핸들러 매핑 정보 (핸들러 == controller)
        initHandler();

        // 어댑터 매핑 정보
        adapters.add(new HandlerAdapter3());
        adapters.add(new HandlerAdapter4());

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
        Object handler = controllerMap.get(requestURI);

        // handler를 바탕으로 이에 맞는 Adapter 조회하기
        HandlerAdapter adapter = getAdapter(handler);

        //adapter에서 handling 하기
        ModelView modelView = adapter.handle(req, resp, handler);

        //viewResolver: "new-form" -> "/WEB-INF/views/new-form.jsp"
        String viewName = modelView.getViewName();
        ViewRenderer viewRenderer = viewResolver(viewName);

        // view 이동
        viewRenderer.process(modelView.getModel(), req ,resp);

    }

    private HandlerAdapter getAdapter(Object handler) {
        for (HandlerAdapter adapter : adapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        // 맞는 Adapter없으면 exception 던지기
        throw new IllegalStateException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private ViewRenderer viewResolver(String viewName) {
        return new ViewRenderer("/WEB-INF/views/" + viewName + ".jsp");
    }

    private void initHandler() {
        controllerMap.put("/mvc-pattern/controller5/3/form", new FormController3());
        controllerMap.put("/mvc-pattern/controller5/3/save", new SaveController3());
        controllerMap.put("/mvc-pattern/controller5/3/list", new ListController3());

        controllerMap.put("/mvc-pattern/controller5/4/form", new FormController4());
        controllerMap.put("/mvc-pattern/controller5/4/save", new SaveController4());
        controllerMap.put("/mvc-pattern/controller5/4/list", new ListController4());
    }
}
