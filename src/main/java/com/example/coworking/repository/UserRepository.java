package com.example.coworking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coworking.entity.User;


public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUsername(String username); // Método necesario para la autenticación
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
