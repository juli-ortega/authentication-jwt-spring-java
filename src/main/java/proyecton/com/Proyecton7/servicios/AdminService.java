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

    @Transactional
    public void createUser(String dni, String first_name, String last_name, String email, String password, String phone_number) throws MiException {
        // create user by gjohn
        try {
            validations(dni, first_name, last_name, email, password, phone_number);
            User new_user = new User();
            new_user.setDni(dni);
            new_user.setFirst_name(first_name);
            new_user.setLast_name(last_name);
            new_user.setEmail(email);
            new_user.setPassword(password);
            new_user.setPhone_number(phone_number);
            new_user.setRol(Roles.COMPRADOR);
            usuarioRepositorio.save(new_user);
        } catch (Exception ex) {
            throw new MiException(ex.toString());
        }

    }

    @Transactional
    public void modifyUser(String dni, String first_name, String last_name, String email, String password, String phone_number) throws MiException {
        try {
            validations(dni, first_name, last_name, email, password, phone_number);
            Optional<User> response = usuarioRepositorio.findById(dni);
            if (response.isPresent()) {
                User newUser = response.get();
                newUser.setFirst_name(first_name);
                newUser.setLast_name(last_name);
                newUser.setPassword(password);
                newUser.setPhone_number(phone_number);
                usuarioRepositorio.save(newUser);
            }

        } catch (Exception ex) {
            throw new MiException(ex.toString());
        }

    }

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

    public void validations(String dni, String first_name, String last_name, String email, String password, String phone_number) throws MiException {
        if (dni.isEmpty() || dni == null) {
            throw new MiException("el dni no puede estar vacio o ser nulo");
        }
        if (first_name.isEmpty() || first_name == null) {
            throw new MiException("el nombre no puede estar vacio o ser nulo");
        }
        if (last_name.isEmpty() || last_name == null) {
            throw new MiException("el apellido no puede estar vacio o ser nulo");
        }
        if (email.isEmpty() || email == null || !email.contains("@") || !email.contains(".com")) {
            throw new MiException("el email no puede estar vacio o ser nulo, debe contener @ y .com");
        }
        if (password.isEmpty() || password == null || password.length() < 6) {
            throw new MiException("la contraseña no puede estar vacio o ser nulo, debe contener almenos 6 caracteres");
        }
        if (phone_number.isEmpty() || phone_number == null || phone_number.length() < 10) {
            throw new MiException("el numero de telefono no puede estar vacio o ser nulo, debe contener almenos 10 caracteres");
        }
    }
}
