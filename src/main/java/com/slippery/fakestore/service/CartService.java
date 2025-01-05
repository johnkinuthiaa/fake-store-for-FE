package com.slippery.fakestore.service;

import com.slippery.fakestore.dto.CartDto;
import com.slippery.fakestore.models.Cart;

public interface CartService {
    CartDto createNewCart(Cart cartDetails);
}
