package com.slippery.fakestore.service;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.models.User;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto login(User user);
    UserDto deleteUser(Long userId);
    UserDto updateUser(Long userId,User user);
    UserDto getAllUsers();
    UserDto getUserById(Long id);
}
