package com.example.java_spring_mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.java_spring_mvc.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User u1);

    List<User> findByEmail(String email);

}
