package com.example.coworking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.coworking.dto.LoginRequest;
import com.example.coworking.dto.LoginResponse;
import com.example.coworking.dto.UserRegisterDTO;
import com.example.coworking.entity.UserEntity;
import com.example.coworking.security.JwtTokenProvider;
import com.example.coworking.service.UserEntityService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;



@RestController
public class AuthController {

    @Autowired
    private UserEntityService userService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @PostMapping("/auth/register")
    public UserEntity save(@RequestBody UserRegisterDTO userDTO){
        return userService.save(userDTO);
    }


    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginDTO){
        Authentication authDTO= new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
    
        Authentication authentication= this.authManager.authenticate(authDTO);
        UserEntity user= (UserEntity) authentication.getPrincipal();
    
        String token= this.jwtTokenProvider.generateToken(authentication);
    
        return new LoginResponse(user.getUsername(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                token);
    }
    

}
