package proyecton.com.Proyecton7.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecton.com.Proyecton7.entities.User;
import proyecton.com.Proyecton7.excepciones.MiException;
import proyecton.com.Proyecton7.respositorios.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepositorio;

    public void create_user(String dni, String first_name, String last_name, String email, String password, String phone_number) throws MiException {
        // create user by gjohn
        User new_user = new User();
        
        //Falta metodo validateUser para corroborar si los parametros fueron ingresados correctamente
        
        try {
            new_user.setDni(dni);
            new_user.setFirst_name(first_name);
            new_user.setLast_name(last_name);
            new_user.setEmail(email);
            new_user.setPassword(password);
            new_user.setPhone_number(phone_number);
            usuarioRepositorio.save(new_user);
        } catch (Exception ex) {
            throw new MiException(ex);
        }

    }
}
