package spring.mvc.mvcpattern.controller2.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller2.Controller2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveController2 implements Controller2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ViewRenderer logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SaveController2.logic");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        //Model에 데이터를 보관하기.
        request.setAttribute("member", member);

        //ViewRenderer 반환하기
        String viewPath = "/WEB-INF/views/member-save.jsp";
        ViewRenderer viewRenderer = new ViewRenderer(viewPath);
        return viewRenderer;
    }
}
