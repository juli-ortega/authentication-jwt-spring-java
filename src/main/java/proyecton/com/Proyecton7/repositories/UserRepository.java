package proyecton.com.Proyecton7.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecton.com.Proyecton7.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
/*
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmailIs(@Param("email") String email);
    Optional<User> findByEmail(String email);*/
}
