package com.example.coworking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.coworking.dto.UserDTO;
import com.example.coworking.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
