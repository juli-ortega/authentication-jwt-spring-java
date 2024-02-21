package proyecton.com.Proyecton7.servicios;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecton.com.Proyecton7.entities.User;
import proyecton.com.Proyecton7.enumeraciones.Roles;
import proyecton.com.Proyecton7.excepciones.MiException;
import proyecton.com.Proyecton7.respositorios.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UserRepository usuarioRepositorio;
    @Autowired
    private UserService userService;
    
    //Creacion de un ADMIN
    @Transactional
    public void createAdmin(String dni, String first_name, String last_name, String email, String password, String phone_number) throws MiException {
        try {
            userService.validations(dni, first_name, last_name, email, password, phone_number);
            User new_user = new User();
            new_user.setDni(dni);
            new_user.setFirst_name(first_name);
            new_user.setLast_name(last_name);
            new_user.setEmail(email);
            new_user.setPassword(password);
            new_user.setPhone_number(phone_number);
            new_user.setRol(Roles.ADMIN);
            usuarioRepositorio.save(new_user);  
        } catch (Exception ex) {
            throw new MiException(ex.toString());
        }
    }
    
    //Eliminacion de usuarios
    @Transactional
    public void deleteUsers(String dni) throws MiException {
        try {
            Optional<User> response = usuarioRepositorio.findById(dni);
            if (response.isPresent()) {
                usuarioRepositorio.deleteAllById(Collections.singleton(dni));
            }
        } catch (Exception ex) {
            throw new MiException(ex.toString());
        }
    }

    public List<User> listUsers() {
        List<User> users = usuarioRepositorio.findAll();
        return users;
    }
}
