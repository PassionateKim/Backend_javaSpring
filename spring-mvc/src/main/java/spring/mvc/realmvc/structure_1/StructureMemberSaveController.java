package spring.mvc.realmvc.structure_1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StructureMemberSaveController {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @RequestMapping("/realmvc/structure_1/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("member-save");
        mv.addObject("member", member);
        return mv;
    }
}
