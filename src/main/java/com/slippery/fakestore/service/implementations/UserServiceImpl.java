package com.slippery.fakestore.service.implementations;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.models.Address;
import com.slippery.fakestore.models.Cart;
import com.slippery.fakestore.models.User;
import com.slippery.fakestore.repository.AddressRepository;
import com.slippery.fakestore.repository.CartRepository;
import com.slippery.fakestore.repository.UserRepository;
import com.slippery.fakestore.service.JwtService;
import com.slippery.fakestore.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

        //        create cart
        Cart cart = new Cart();
        cart.setCreatedOn(LocalDateTime.now());
        cart.setProducts(null);
        cart.setTotalItems(0);
        cart.setUser(toSave);
        cartRepository.save(cart);

        toSave.setCart(cart);

//        save the address
        Address userAddress =userDetails.getAddress();

        userAddress.setUser(toSave);
        addressRepository.save(userAddress);
        userRepository.save(toSave);


        response.setMessage("new user created");
        response.setStatusCode(200);
        response.setUser(toSave);

        return response;
    }

    @Override
    public UserDto login(User user) {
        UserDto response =new UserDto();
        Optional<User> existingUser = Optional.ofNullable(userRepository.findUserByUsername(user.getUsername()));
        if(existingUser.isEmpty()){
            response.setMessage("user does not exist!");
            response.setStatusCode(401);
            return response;
        }
        Authentication authentication =authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                user.getUsername(),user.getPassword()
        ));
        if(authentication.isAuthenticated()){
            response.setJwtToken(jwtService.generateJwtToken(user.getUsername()));
            response.setMessage("User logged in successfully");
            response.setStatusCode(200);
        }

        return response;
    }

    @Override
    public UserDto deleteUser(Long userId) {
        UserDto response =new UserDto();
        Optional<User> user =userRepository.findById(userId);
        if(user.isEmpty()){
            response.setMessage("user does not exist");
            response.setStatusCode(404);
            return response;
        }
        userRepository.delete(user.get());
        response.setMessage("User has been deleted");
        response.setStatusCode(304);
        return response;
    }

    @Override
    public UserDto updateUser(Long userId, User user) {
        UserDto response =new UserDto();
        Optional<User> existingUser =userRepository.findById(userId);
        Optional<User> userWithUsername = Optional.ofNullable(userRepository.findUserByUsername(user.getUsername()));
        if(userWithUsername.isPresent()){
            response.setMessage("User with that username found...please try again with another username");
            response.setStatusCode(200);
            return response;
        }
        if(existingUser.isEmpty()){
            response.setMessage("User was not found");
            response.setStatusCode(200);
            return response;
        }
        existingUser.
                get().
                setFirstname(!(user.getFirstname() ==null)&& !user.getFirstname().isEmpty() ?user.getFirstname():existingUser.get().getFirstname());
        existingUser.get()
                .setLastname(!(user.getLastname() ==null)&& !user.getLastname().isEmpty() ?user.getLastname():existingUser.get().getLastname());
        existingUser.
                get()
                .setPassword(
                        !(user.getPassword() ==null)
                                && !user.getPassword().isEmpty()
                                ? passwordEncoder.encode(user.getPassword()) :
                                existingUser.get().getPassword()
                        );
        existingUser.get().setEmail(!(user.getEmail() ==null) &&!user.getEmail().isEmpty()?user.getEmail():existingUser.get().getEmail());
        existingUser.get().setPhone(!(user.getPhone() ==null) &&!user.getPhone().isEmpty()? user.getPhone() : existingUser.get().getPhone());
        existingUser
                .get().setUsername(!(user.getUsername() ==null)
                &&!user.getUsername().isEmpty()
                                ? user.getUsername() : existingUser.get().getUsername());
        userRepository.save(existingUser.get());
        response.setStatusCode(200);
        response.setMessage("User updated successfully");
        response.setUser(existingUser.get());
        return response;
    }

    @Override
    public UserDto getAllUsers() {
        UserDto response =new UserDto();
        List<User> allUsers =userRepository.findAll();

        if(allUsers.isEmpty()){
            response.setMessage("UserList is empty");
            response.setStatusCode(200);
        }
        response.setMessage("All users");
        response.setUsers(allUsers);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserDto response =new UserDto();
        Optional<User> user =userRepository.findById(id);
        if(user.isEmpty()){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("User with id "+id);
        response.setUser(user.get());
        response.setStatusCode(200);
        return response;
    }

}

