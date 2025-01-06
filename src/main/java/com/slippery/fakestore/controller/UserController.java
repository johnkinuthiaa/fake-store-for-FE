package com.slippery.fakestore.controller;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.models.User;
import com.slippery.fakestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/delete/id")
    public ResponseEntity<UserDto> deleteUser(@RequestParam Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
    @PutMapping("/update/user")
    public ResponseEntity<UserDto> updateUser(@RequestParam Long userId,@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(userId,user));
    }
    @GetMapping("/get/all/users")
    public ResponseEntity<UserDto> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/get/user")
    public ResponseEntity<UserDto> getUserById(@RequestParam Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody User user){
        return ResponseEntity.ok(userService.login(user));
    }
}
