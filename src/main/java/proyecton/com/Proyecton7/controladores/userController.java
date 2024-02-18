package proyecton.com.Proyecton7.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecton.com.Proyecton7.entities.User;
import proyecton.com.Proyecton7.enumeraciones.Roles;
import proyecton.com.Proyecton7.excepciones.MiException;
import proyecton.com.Proyecton7.respositorios.UserRepository;
import proyecton.com.Proyecton7.servicios.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class userController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String user() {
        return "profile.html";
    }


    @GetMapping("/register")
    public String registrarse() {
        return "register.html";
    }

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
    @GetMapping("/login")
    public String iniciar() {
        try {
            return "login.html";
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("lpm");
        return null;

    }

    @PostMapping("/signin")
    public String sign(@RequestParam String email, @RequestParam String password) throws MiException {
        try {
            Optional<User> response = userRepository.serchForEmail(email);
            if (response.isPresent()){
                User user = response.get();
                if (user.getRol().equals(Roles.ADMIN)){
                    return "redirect:/admin/admin";
                }

            }
            
        } catch (Exception ex) {
            throw new MiException(ex.toString());
            //model.put("mistake", ex.getMessage());  //SE DEBE CREAR EL .getMessage CORRESPONDIENTE 
            
        }
        
        return "redirect:/"; //Modificar a vista de usuario

    }
}
