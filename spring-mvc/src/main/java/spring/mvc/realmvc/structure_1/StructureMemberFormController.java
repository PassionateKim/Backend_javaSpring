package spring.mvc.realmvc.structure_1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StructureMemberFormController{
    @RequestMapping("/structure/form")
    public ModelAndView process() {
        System.out.println("StructureMemberFormController.process");
        return new ModelAndView("new-form");
    }
}
