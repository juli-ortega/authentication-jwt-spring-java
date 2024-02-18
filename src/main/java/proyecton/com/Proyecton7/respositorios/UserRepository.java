package proyecton.com.Proyecton7.respositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecton.com.Proyecton7.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
