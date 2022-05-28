package hello.hellospring.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HelloController {
//   url 뒤가 hello
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        //템플릿의 hello로 가서 렌더링해라
        return "hello";
    }
    //mvc와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    //api
    @GetMapping("hello-string")
    @ResponseBody  //body에 내가 이걸 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    //api ,, 객체체
   @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        //멤버변수는 직접 접근할 수 없도록 private로 선언해야 한다.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
