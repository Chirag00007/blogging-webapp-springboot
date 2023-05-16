package com.chirag.main.services;

import com.chirag.main.dto.UserDto;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUser(Integer id);

}
