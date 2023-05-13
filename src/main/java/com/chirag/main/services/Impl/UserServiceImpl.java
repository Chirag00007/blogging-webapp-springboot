package com.chirag.main.services.Impl;

import com.chirag.main.entities.User;
import com.chirag.main.exceptions.ResourceNotFoundException;
import com.chirag.main.payloads.UserDto;
import com.chirag.main.repositiories.UserRepositry;
import com.chirag.main.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.convertToEntity(userDto);
       User savedUser =  this.userRepositry.save(user);
       return this.convertToDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto user, Integer id) {
        User user1 = this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setAbout(user.getAbout());
        User updatedUser = this.userRepositry.save(user1);
        return this.convertToDto(updatedUser);
    }

        @Override
        public UserDto getUserById (Integer id){
        User user = this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return this.convertToDto(user);
        }

        @Override
        public List<UserDto> getAllUsers () {
            List<User> users = this.userRepositry.findAll();
            return users.stream().map(this::convertToDto).collect(java.util.stream.Collectors.toList());
        }

        @Override
        public void deleteUser (Integer id){
            User user = this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
            this.userRepositry.delete(user);
        }

        private User convertToEntity (UserDto  userDto){

            //We are using ModelMapper to convert UserDto to User
            User user = modelMapper.map(userDto, User.class);

            //this is the code without using ModelMapper
//            User user = new User();
//            user.setId(userDto.getId());
//            user.setName(userDto.getName());
//            user.setEmail(userDto.getEmail());
//            user.setPassword(userDto.getPassword());
//            user.setAbout(userDto.getAbout());
          return user;
        }

        public UserDto convertToDto (User user){
            UserDto userDto = modelMapper.map(user, UserDto.class);
            return userDto;
        }


}
