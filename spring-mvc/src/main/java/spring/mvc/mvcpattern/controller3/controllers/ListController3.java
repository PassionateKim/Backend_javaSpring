package spring.mvc.mvcpattern.controller3.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.controller1.Controller1;
import spring.mvc.mvcpattern.controller3.Controller3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ListController3 implements Controller3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView logic(Map<String, String> paramMap){
        System.out.println("ListController3.logic");
        List<Member> members = memberRepository.findAll();

        String viewPath = "member-list";
        ModelView modelView = new ModelView(viewPath);
        modelView.getModel().put("members", members);

        return modelView;
    }
}
