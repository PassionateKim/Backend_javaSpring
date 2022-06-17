package spring.mvc.mvcpattern.controller4.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.controller4.Controller4;

import java.util.List;
import java.util.Map;

public class ListController4 implements Controller4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String logic(Map<String, String> paramMap, Map<String, Object> model){
        System.out.println("ListController4.logic");
        List<Member> members = memberRepository.findAll();

        String viewPath = "member-list";

        //model에 데이터 넣기
        model.put("members", members);

        return viewPath;
    }
}
