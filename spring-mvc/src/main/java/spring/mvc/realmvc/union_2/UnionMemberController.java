package spring.mvc.realmvc.union_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/union")
public class UnionMemberController {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("member-save");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping("/list")
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("member-list");

        mv.addObject("members", members);
        return mv;
    }
}
