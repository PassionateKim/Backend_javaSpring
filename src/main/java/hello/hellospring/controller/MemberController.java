package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//Controller annotation 이 잇으면 객체가 컨테이너에 생성되어
// 스프링이 관리하게 된다.
@Controller
public class MemberController {

    //MemberService같은건 회원정보 말고도 주문 등 다른 컨트롤러에서도
    //공통으로 활용될 수 있으므로 객체 선언해서 사용하기보단 spring에 연결해서 사용
    private final MemberService memberService;

    //Controller 객체가 생성될 때 Autowired 가 있으면
    //스프링 컨테이너에 있는 memberService를 스프링이 가져다가 연결을 시켜줍니다.
   @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }
}
