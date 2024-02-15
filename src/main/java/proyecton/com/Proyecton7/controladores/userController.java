package proyecton.com.Proyecton7.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecton.com.Proyecton7.excepciones.MiException;

@Controller
@RequestMapping("/userController")
public class userController {
    @GetMapping("/user")
    public String user(){
        return "index.html" ;
              
    }
    @GetMapping("/signin")
    public String iniciar() {
        try {
                 return "signin.html";
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("lpm");
        return null;
   
    }

    @GetMapping("/register")
    public String registrarse() {
        return "register.html";
    }

    @PostMapping("/registered")
    public String registered(@RequestParam String dni, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String password, @RequestParam String phone_number) throws MiException {
        try{
            System.out.println("DNI: " + dni);
            System.out.println("Nombre: " + first_name);
            System.out.println("Apellido: " + last_name);
            System.out.println("Correo Electrónico: " + email);
            System.out.println("Contraseña: " + password);
            System.out.println("Teléfono: " + phone_number);
        }catch (Exception ex){
            throw new MiException(ex);
        }

        return "signin.html";
    }
    
    @PostMapping ("/sign")
    public String sign(@RequestParam String email, @RequestParam String password) throws MiException{
        try {
            System.out.println("Correo Electrónico: " + email);
            System.out.println("Contraseña: " + password);
        } catch (Exception ex) {
            throw new MiException(ex);
        }
        return"index.html"; //Modificar a vista de usuario

    }
}
