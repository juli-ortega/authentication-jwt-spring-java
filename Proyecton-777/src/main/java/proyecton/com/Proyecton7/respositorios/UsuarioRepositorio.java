package proyecton.com.Proyecton7.respositorios;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecton.com.Proyecton7.entities.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{

   
    
}
