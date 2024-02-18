package proyecton.com.Proyecton7.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecton.com.Proyecton7.excepciones.MiException;
import proyecton.com.Proyecton7.servicios.UserService;

@Controller
@RequestMapping("/admin")
public class adminController {
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome.html" ;
    }
    @GetMapping("/register")
    public String registrarse() {
        return "register.html";
    }
    @Autowired
    UserService userService;
    @PostMapping("/registered")
    public String registered(@RequestParam String dni, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String password, @RequestParam String phone_number, ModelMap model) throws MiException {

        try {

            userService.createUser(dni,
                    first_name,
                    last_name,
                    email,
                    password,
                    phone_number);
            //Listo para crear un usuario, falta conectar en UserService correctamente con BASEDEDATOS
            //userService.create_user(dni, first_name, last_name, email, password, phone_number);
            model.put("success", "Usuario registrado correctamente");


        } catch (Exception ex) {
            throw new MiException(ex.toString());   //SE DEBE ELIMINAR ESTA PARTE

            //model.put("mistake", ex.getMessage());  //SE DEBE CREAR EL .getMessage CORRESPONDIENTE
            //return "register.html";
        }

        return "login.html";
    }
}
