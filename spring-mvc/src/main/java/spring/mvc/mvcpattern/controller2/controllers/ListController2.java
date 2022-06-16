package spring.mvc.mvcpattern.controller2.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.ViewRenderer;
import spring.mvc.mvcpattern.controller2.Controller2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListController2 implements Controller2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ViewRenderer logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ListController2.logic");
        List<Member> members = memberRepository.findAll();

        //request에 데이터 넣기
        request.setAttribute("members", members);

        //viewRenderer 반환하기
        String viewPath = "/WEB-INF/views/member-list.jsp";
        ViewRenderer viewRenderer = new ViewRenderer(viewPath);
        return viewRenderer;
    }
}
