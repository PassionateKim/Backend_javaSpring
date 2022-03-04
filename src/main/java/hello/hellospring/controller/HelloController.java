package hello.hellospring.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
//   url 뒤가 hello
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        //템플릿의 hello로 가서 렌더링해라
        return "hello";
    }

}
