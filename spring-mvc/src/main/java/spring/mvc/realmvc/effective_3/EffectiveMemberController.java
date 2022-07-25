package spring.mvc.realmvc.effective_3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;

import java.util.List;
@Controller
@RequestMapping("/effective")
public class EffectiveMemberController {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/form")
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(name, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "member-save";
    }

    @GetMapping("/list")
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "member-list";
    }
}
