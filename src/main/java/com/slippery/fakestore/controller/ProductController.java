package com.slippery.fakestore.controller;

import com.slippery.fakestore.dto.ProductDto;
import com.slippery.fakestore.models.Product;
import com.slippery.fakestore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    @PostMapping("/create/array")
    public ResponseEntity<String> createProductsWithList(@RequestBody List<Product> productList){
        for(Product product:productList){
            service.createNewProduct(product);
        }
        return ResponseEntity.ok("products created");
    }
}
