package spring.mvc.mvcpattern.controller3.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.ModelView;
import spring.mvc.mvcpattern.controller3.Controller3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SaveController3 implements Controller3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView logic(Map<String, String> paramMap){
        System.out.println("SaveController3.logic");

        //데이터를 저장소에 보관하기.
        String name = paramMap.get("name");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        //View와 데이터 modelView에 넘기기
        String viewPath = "member-save";
        ModelView modelView = new ModelView(viewPath);
        modelView.getModel().put("member", member);

        return modelView;
    }
}
