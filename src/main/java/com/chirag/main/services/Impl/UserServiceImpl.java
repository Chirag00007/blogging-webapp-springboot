package com.chirag.main.services.Impl;

import com.chirag.main.entities.User;
import com.chirag.main.exceptions.ResourceNotFoundException;
import com.chirag.main.payloads.UserDto;
import com.chirag.main.repositiories.UserRepositry;
import com.chirag.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositry userRepositry;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.convertToEntity(userDto);
       User savedUser =  this.userRepositry.save(user);
       return this.convertToDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto user, Integer id) {
        User user1 = this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return null;
    }

        @Override
        public UserDto getUserById (Integer id){
            return null;
        }

        @Override
        public List<UserDto> getAllUsers () {
            return null;
        }

        @Override
        public void deleteUser (Integer id){

        }

        private User convertToEntity (UserDto userDto){
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setAbout(userDto.getAbout());
            return user;
        }

        public UserDto convertToDto (User user){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setAbout(user.getAbout());
            return userDto;
        }


}
