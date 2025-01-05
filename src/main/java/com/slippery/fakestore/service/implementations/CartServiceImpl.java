package com.slippery.fakestore.service.implementations;

import com.slippery.fakestore.dto.CartDto;
import com.slippery.fakestore.models.Cart;
import com.slippery.fakestore.models.Product;
import com.slippery.fakestore.models.User;
import com.slippery.fakestore.repository.CartRepository;
import com.slippery.fakestore.repository.ProductRepository;
import com.slippery.fakestore.repository.UserRepository;
import com.slippery.fakestore.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CartDto addItem(Long userId,Long cartId, Long productId) {
        CartDto response =new CartDto();
        Optional<User> existingUser =userRepository.findById(userId);
        Optional<Product> product =productRepository.findById(productId);
        Optional<Cart> cart =cartRepository.findById(cartId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        if(product.isEmpty()){
            response.setMessage("product not found");
            response.setStatusCode(404);
            return response;
        }
        if(cart.isEmpty()){
            response.setMessage("cart not found");
            response.setStatusCode(404);
            return response;
        }
        if(!existingUser.get().getId().equals(cart.get().getUser().getId())){
            response.setMessage("cart does not belong to the user");
            response.setStatusCode(404);
            return response;
        }
        List<Product> products =cart.get().getProducts();
        products.add(product.get());

        cart.get().setProducts(products);
        cart.get().setTotalItems(products.size());
        cartRepository.save(cart.get());
        response.setMessage("Product added to cart");
        response.setStatusCode(200);
        response.setCart(cart.get());

        return response;
    }

    @Override
    public CartDto removeItemFromCart(Long userId, Long cartId, Long productId) {
        CartDto response =new CartDto();
        Optional<User> existingUser =userRepository.findById(userId);
        Optional<Product> product =productRepository.findById(productId);
        Optional<Cart> cart =cartRepository.findById(cartId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        if(product.isEmpty()){
            response.setMessage("product not found");
            response.setStatusCode(404);
            return response;
        }
        if(cart.isEmpty()){
            response.setMessage("cart not found");
            response.setStatusCode(404);
            return response;
        }
        if(!existingUser.get().getId().equals(cart.get().getUser().getId())){
            response.setMessage("cart does not belong to the user");
            response.setStatusCode(404);
            return response;
        }

        List<Product> products =cart.get().getProducts();
        if(!products.contains(product.get())){
            response.setMessage("Item was not found in cart");
            response.setStatusCode(404);
            return response;
        }
        products.remove(product.get());
        cart.get().setProducts(products);
        cartRepository.save(cart.get());
        response.setMessage("Item removed from cart");
        response.setStatusCode(200);

        return response;
    }

    @Override
    public CartDto clearCart(Long userId, Long cartId) {
        CartDto response =new CartDto();
        Optional<User> existingUser =userRepository.findById(userId);
        Optional<Cart> cart =cartRepository.findById(cartId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        if(cart.isEmpty()){
            response.setMessage("cart not found");
            response.setStatusCode(404);
            return response;
        }
        if(!existingUser.get().getId().equals(cart.get().getUser().getId())){
            response.setMessage("cart does not belong to the user");
            response.setStatusCode(404);
            return response;
        }

        List<Product> products =cart.get().getProducts();
        if(products.isEmpty()){
            response.setMessage("your cart is already empty");
            response.setStatusCode(404);
            return response;
        }
        products.clear();
        cart.get().setProducts(products);
        cart.get().setTotalItems(products.size());
        cartRepository.save(cart.get());
        response.setMessage("your cart has been cleared");
        response.setStatusCode(200);
        return response;
    }
}
