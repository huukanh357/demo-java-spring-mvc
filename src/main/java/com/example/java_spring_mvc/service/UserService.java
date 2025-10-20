package com.example.java_spring_mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.java_spring_mvc.domain.User;
import com.example.java_spring_mvc.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "hello service";
    }

    public List<User> getAllUseres() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUseresByEmaill(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public User handleUpdateUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(long Id) {
        return this.userRepository.findById(Id).orElse(null);
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
}
