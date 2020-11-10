package franbusleiman.PetClinic.PetClinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "/index.html", "/index"})
    public String setanithing(Model model){
        return "index.html";
    }
}
