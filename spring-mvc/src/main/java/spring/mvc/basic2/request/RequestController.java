package spring.mvc.basic2.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.mvc.basic.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@Controller
public class RequestController {
    @ResponseBody
    @GetMapping("/request-mapping-get")
    public String mappingGet() {
        log.info("mapping-get");
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-mapping-path/{userId}")
    public String mappingPathVariable(@PathVariable("userId") String data) {
        log.info("mapping Path userId={}", data);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-mapping-header")
    public String mappingHeader(HttpServletRequest request,
                                HttpServletResponse response,
                                Locale locale,
                                @RequestHeader("host") String host,
                                @CookieValue(value = "myCookie", required = false) String cookie){

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("locale={}", locale);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-mapping-param")
    public String mappingParam(@RequestParam("membername") String memberName,
                               @RequestParam("age") int age) {
        log.info("membername={}, memberage={}", memberName, age);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-mapping-model")
    public String mappingModel(@ModelAttribute Member member) {
        log.info("membername={}, memberage={}", member.getName(), member.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-mapping-json")
    public String mappingJson(@RequestBody Member member) {
        log.info("membername={}, memberage={}", member.getName(), member.getAge());
        return "ok";
    }
}

