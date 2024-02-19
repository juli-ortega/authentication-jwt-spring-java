package proyecton.com.Proyecton7.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecton.com.Proyecton7.excepciones.MiException;
import proyecton.com.Proyecton7.servicios.AdminService;

@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome.html" ;
    }
    
    @PostMapping("/createAdmin")
    public String registered(@RequestParam String dni, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String password, @RequestParam String phone_number, ModelMap model) throws MiException {
        try {
            adminService.createAdmin(dni, first_name, last_name, email, password, phone_number);
            model.put("success", "Administrador registrado correctamente");

        } catch (Exception ex) {  
            model.put("mistake", ex.getMessage());  
            return "adminHome.html";
        }
        return "adminHome.html";
    }
}
