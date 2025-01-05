package com.slippery.fakestore.service;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.models.User;

public interface UserService {
    UserDto createUser(UserDto user);
}
