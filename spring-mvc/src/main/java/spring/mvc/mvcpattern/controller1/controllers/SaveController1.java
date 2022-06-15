package spring.mvc.mvcpattern.controller1.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.controller1.Controller1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveController1 implements Controller1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public void logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SaveController1.logic");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        //Model에 데이터를 보관하기.
        request.setAttribute("member", member);

        //View로 넘기기
        String viewPath = "/WEB-INF/views/member-save.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
