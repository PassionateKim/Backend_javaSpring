package spring.mvc.basic2.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.mvc.basic.Member;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseController {

    @GetMapping("/response-view")
    public String responseViewV1(Model model) {
        model.addAttribute("data", "helloWorld!");

        return "response/hello";
    }

    @GetMapping("/response-body-string")
    public void responseStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");

    }

    @GetMapping("/response-body-string2")
    public ResponseEntity<String> responseStringV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string3")
    public String responseStringV3() {
        return "ok";
    }

    @GetMapping("/response-body-json")
    public ResponseEntity<Member> responseJsonV1() {
        Member member = new Member();
        member.setId(1L);
        member.setName("memberA");
        member.setAge(20);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-json2")
    public Member responseJsonV2() {
        Member member = new Member();

        member.setId(1L);
        member.setName("memberA");
        member.setAge(20);

        return member;
    }

    @ResponseBody
    @GetMapping("/response-get-json-body")
    public String responseGetJsonV1(@RequestBody String data) {
        log.info("data={}", data);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/response-get-json-body2")
    public String responseGetJsonV1(@RequestBody Member member) {
        log.info("id={}, name={}, age={}", member.getId(),member.getName(), member.getAge());
        return "ok";
    }
}
