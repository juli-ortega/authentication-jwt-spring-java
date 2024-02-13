package proyecton.com.Proyecton7.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registrar") //localhost:8081/registrar
public class encontrarTrabajo {
    
    @GetMapping("/registrar") //localhost:8081/registrar/registrar
    public String paginaTrabajo(){
        return "registar_trabajo.html";
    }
    
    @PostMapping("/registro")
    public String trabajo(@RequestParam String trabajo){
        System.out.println("Llego correctamente");
        
        return "registar_trabajo.html";
    }
}
