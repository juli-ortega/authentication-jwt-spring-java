package proyecton.com.Proyecton7.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecton.com.Proyecton7.entities.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images  , String> {
}
