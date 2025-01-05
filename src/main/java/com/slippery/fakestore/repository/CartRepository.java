package com.slippery.fakestore.repository;

import com.slippery.fakestore.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
