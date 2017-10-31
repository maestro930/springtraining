package priv.rdo.sdaspring.task08;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Task08SwaggerController {
    @RequestMapping(value = "/documentation")
    public String index(){
        return "redirect:swagger-ui.html";
    }
}
