package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    List<User> findAll();
}
