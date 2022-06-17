package spring.mvc.mvcpattern.controller5.adapters;

import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.controller4.Controller4;
import spring.mvc.mvcpattern.controller5.HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HandlerAdapter4 implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        if (handler instanceof Controller4) {
            return true;
        }
        return false;
    }

    // 여기서 controller를 호출해서 logic을 사용한다.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Map<String, String> paramMap = getparamMap(request);
        Map<String, Object> model = new HashMap<>();
        Controller4 controller4 = (Controller4) handler;

        // controller4 에서 model에 값을 넣고, viewName과 model을 modelView에 담아 modelView return
        String viewName = controller4.logic(paramMap, model);
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);
        return modelView;
    }

    private Map<String,String> getparamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
