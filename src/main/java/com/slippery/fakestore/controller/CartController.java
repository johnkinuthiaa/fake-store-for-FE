package com.slippery.fakestore.controller;

import com.slippery.fakestore.dto.CartDto;
import com.slippery.fakestore.repository.CartRepository;
import com.slippery.fakestore.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
