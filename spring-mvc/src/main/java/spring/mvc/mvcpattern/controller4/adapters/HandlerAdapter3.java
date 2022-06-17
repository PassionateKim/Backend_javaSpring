package spring.mvc.mvcpattern.controller4.adapters;

import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.controller3.Controller3;
import spring.mvc.mvcpattern.controller4.HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HandlerAdapter3 implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        if (handler instanceof Controller3) {
            return true;
        }
        return false;
    }

    // 여기서 controller를 호출해서 logic을 사용한다.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Map<String, String> paramMap = getparamMap(request);

        Controller3 controller3 = (Controller3) handler;
        ModelView modelView = controller3.logic(paramMap);

        return modelView;
    }

    private Map<String,String> getparamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
