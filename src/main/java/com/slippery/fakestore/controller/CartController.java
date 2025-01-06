package com.slippery.fakestore.controller;

import com.slippery.fakestore.dto.CartDto;
import com.slippery.fakestore.repository.CartRepository;
import com.slippery.fakestore.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PutMapping("/add/product")
    public ResponseEntity<CartDto> addItem(@RequestParam Long userId,
                                          @RequestParam Long cartId,
                                          @RequestParam Long productId){
        return ResponseEntity.ok(cartService.addItem(userId, cartId, productId));


    }
    @PutMapping("/remove-item")
    public ResponseEntity<CartDto> removeItemFromCart(
            @RequestParam Long userId,
            @RequestParam Long cartId,
            @RequestParam Long productId){
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, cartId, productId));

    }
    @PutMapping("/clear")
    public ResponseEntity<CartDto> clearCart(@RequestParam Long userId, @RequestParam Long cartId){
        return ResponseEntity.ok(cartService.clearCart(userId, cartId));

    }
    @GetMapping("/get/cart/id")
    public ResponseEntity<CartDto> findCartById(@RequestParam Long userId,@RequestParam Long cartId){
        return ResponseEntity.ok(cartService.findCartById(userId, cartId));
    }
    @GetMapping("/get/all/carts")
    public ResponseEntity<CartDto> getAllCarts(){
        return ResponseEntity.ok(cartService.getAllCarts());
    }
}
