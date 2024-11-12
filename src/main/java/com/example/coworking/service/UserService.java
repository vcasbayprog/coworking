package com.example.coworking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.coworking.dto.UserDTO;
import com.example.coworking.entity.Role;
import com.example.coworking.entity.User;
import com.example.coworking.mapper.UserMapper;
import com.example.coworking.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO, String rawPassword) {
        // Verifica si ya existe un usuario con el mismo email o username
        checkEmailAndUsernameUniqueness(userDTO);

        // Mapea el UserDTO a User
        User user = userMapper.userDTOToUser(userDTO);

        // Verifica que la contraseña no esté vacía y codifícala
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Codifica la contraseña y asigna al usuario
        user.setPassword(passwordEncoder.encode(rawPassword));

        // Asigna un rol por defecto si no está presente
        user.setRole(user.getRole() != null ? user.getRole() : Role.USER);

        // Guarda el usuario en la base de datos
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    private void checkEmailAndUsernameUniqueness(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }
    }
}
