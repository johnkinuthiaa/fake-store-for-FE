package com.slippery.fakestore.service;

import com.slippery.fakestore.dto.CartDto;
import com.slippery.fakestore.models.Cart;

public interface CartService {
    CartDto addItem(Long userId,Long cartId,Long productId);
    CartDto removeItemFromCart(Long userId,Long cartId,Long productId);
    CartDto clearCart(Long userId,Long cartId);

}
