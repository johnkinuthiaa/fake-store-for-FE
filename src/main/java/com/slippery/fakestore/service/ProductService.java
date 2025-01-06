package com.slippery.fakestore.service;

import com.slippery.fakestore.dto.ProductDto;
import com.slippery.fakestore.models.Product;

public interface ProductService {
    ProductDto createNewProduct(Product product);
    ProductDto updateProduct(Product product);
    ProductDto findProductById(Long id);
    ProductDto deleteProductById(Long id);
    ProductDto allProduct();
    ProductDto getProductByName(String name);
    ProductDto getProductByCategory(String category);
}
