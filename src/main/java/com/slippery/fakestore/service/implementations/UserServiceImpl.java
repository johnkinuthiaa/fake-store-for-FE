package com.slippery.fakestore.service.implementations;

import com.slippery.fakestore.dto.UserDto;
import com.slippery.fakestore.models.Address;
import com.slippery.fakestore.models.Cart;
import com.slippery.fakestore.models.User;
import com.slippery.fakestore.repository.AddressRepository;
import com.slippery.fakestore.repository.CartRepository;
import com.slippery.fakestore.repository.UserRepository;
import com.slippery.fakestore.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
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
        cart.setTotalItems(cart.getProducts().size());
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

}

