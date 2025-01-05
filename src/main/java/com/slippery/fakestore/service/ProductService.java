package com.slippery.fakestore.service;

import com.slippery.fakestore.dto.ProductDto;
import com.slippery.fakestore.models.Product;

public interface ProductService {
    ProductDto createNewProduct(Product product);
}
