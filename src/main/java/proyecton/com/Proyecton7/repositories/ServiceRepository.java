package proyecton.com.Proyecton7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecton.com.Proyecton7.entities.Service;


@Repository
public interface ServiceRepository extends JpaRepository<Service,String> {
}
