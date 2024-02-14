package proyecton.com.Proyecton7.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    @GetMapping("/iniciar")
    public String iniciar() {
        return "iniciar.html";
    }
    @GetMapping("/registrarse")
    public String registrarse() {
        return "registrase.html";
    }
    
}
