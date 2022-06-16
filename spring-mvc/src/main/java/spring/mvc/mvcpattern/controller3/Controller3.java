package spring.mvc.mvcpattern.controller3;

import spring.mvc.mvcpattern.ModelView;

import java.util.Map;

public interface Controller3 {

    ModelView logic(Map<String, String> paramMap);
}
