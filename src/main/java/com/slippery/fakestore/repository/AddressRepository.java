package com.slippery.fakestore.repository;

import com.slippery.fakestore.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
