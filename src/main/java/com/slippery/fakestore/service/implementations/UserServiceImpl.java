package com.slippery.fakestore.service.implementations;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.models.Address;
import com.slippery.fakestore.models.User;
import com.slippery.fakestore.repository.AddressRepository;
import com.slippery.fakestore.repository.UserRepository;
import com.slippery.fakestore.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        UserDto response =new UserDto();
        User user =userRepository.findUserByUsername(userDetails.getUser().getUsername());
        if(user !=null){
            response.setMessage("User with username "+user.getUsername()+" already exists");
            response.setStatusCode(400);
            return response;
        }
//        save the user
        User toSave =userDetails.getUser();
        toSave.setCreatedOn(LocalDateTime.now());
        toSave.setUserAddress(userDetails.getAddress());
        toSave.setPassword(passwordEncoder.encode(toSave.getPassword()));
        userRepository.save(toSave);
//        save the address
        Address userAddress =userDetails.getAddress();
        userAddress.setUser(toSave);
        addressRepository.save(userAddress);
        response.setMessage("new user created");
        response.setStatusCode(200);
        response.setUserDetails(userDetails);

        return response;
    }
}
