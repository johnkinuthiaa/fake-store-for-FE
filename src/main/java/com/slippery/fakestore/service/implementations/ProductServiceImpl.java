package com.slippery.fakestore.service.implementations;

import com.slippery.fakestore.dto.ProductDto;
import com.slippery.fakestore.models.Product;
import com.slippery.fakestore.repository.ProductRepository;
import com.slippery.fakestore.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto createNewProduct(Product product) {
        ProductDto response =new ProductDto();
        product.setCreatedOn(LocalDateTime.now());
        repository.save(product);
        return response;
    }
}
