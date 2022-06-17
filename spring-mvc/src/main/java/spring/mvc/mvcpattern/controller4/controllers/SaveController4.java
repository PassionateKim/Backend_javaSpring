package spring.mvc.mvcpattern.controller4.controllers;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;
import spring.mvc.mvcpattern.controller4.Controller4;

import java.util.Map;

public class SaveController4 implements Controller4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String logic(Map<String, String> paramMap, Map<String, Object> model){
        System.out.println("SaveController4.logic");

        //데이터를 저장소에 보관하기.
        String name = paramMap.get("name");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        //View와 데이터 modelView에 넘기기
        String viewPath = "member-save";
        model.put("member", member);

        return viewPath;
    }
}
