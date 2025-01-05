package com.slippery.fakestore.controller;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDetails){
        return ResponseEntity.ok(userService.createUser(userDetails));
    }
}
