package spring.mvc.realmvc.effective_3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
@RequestMapping("/effective")
public class EffectiveMemberController {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/form")
    public String newForm() {
        return "new-form";
    }

    @RequestMapping("/save")
    public String save(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(name, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "member-save";
    }

    @RequestMapping("/list")
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "member-list";
    }
}
