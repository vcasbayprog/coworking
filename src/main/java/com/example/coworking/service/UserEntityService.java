package com.example.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.coworking.dto.UserRegisterDTO;
import com.example.coworking.entity.UserAuthority;
import com.example.coworking.entity.UserEntity;
import com.example.coworking.repository.UserEntityRepository;

@Service
public class UserEntityService {

private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    public UserEntity save(UserRegisterDTO userDTO){
        UserEntity user= new UserEntity(
            null,
            userDTO.username(),
            this.passwordEncoder.encode(userDTO.password()),
            userDTO.email(),
            List.of(UserAuthority.READ)
        );
        return this.repository.save(user);
    }
}
