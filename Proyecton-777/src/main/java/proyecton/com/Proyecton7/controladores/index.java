package proyecton.com.Proyecton7.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class index {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
