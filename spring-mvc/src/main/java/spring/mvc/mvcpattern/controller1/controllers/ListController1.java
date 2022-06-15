package spring.mvc.mvcpattern.controller1.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.controller1.Controller1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListController1 implements Controller1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public void logic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ListController1.logic");
        List<Member> members = memberRepository.findAll();

        //request에 데이터 넣기
        request.setAttribute("members", members);

        //list jsp로 이동시키기
        String viewPath = "/WEB-INF/views/member-list.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
