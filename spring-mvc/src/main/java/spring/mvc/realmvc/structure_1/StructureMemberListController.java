package spring.mvc.realmvc.structure_1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class StructureMemberListController {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @RequestMapping("/realmvc/structure_1/list")
    public ModelAndView process() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("member-list");

        mv.addObject("members", members);
        return mv;
    }
}
