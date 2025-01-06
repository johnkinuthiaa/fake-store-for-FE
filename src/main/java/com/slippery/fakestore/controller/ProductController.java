package com.slippery.fakestore.controller;

import com.slippery.fakestore.dto.ProductDto;
import com.slippery.fakestore.models.Product;
import com.slippery.fakestore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

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
    @PostMapping("/create/product")
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.createNewProduct(product));
    }
    @PutMapping("/update/product")
    public ProductDto updateProduct(Product product){
        return null;
    }
    @GetMapping("/get/products/id")
    public ResponseEntity<ProductDto> findProductById(@RequestParam Long id){
        return ResponseEntity.ok(service.findProductById(id));
    }
    @DeleteMapping("/delete/products")
    public ResponseEntity<ProductDto> deleteProductById(@RequestParam Long id){
        return ResponseEntity.ok(service.deleteProductById(id));
    }
    @GetMapping("/all/products")
    public ResponseEntity<ProductDto> allProduct(){
        return ResponseEntity.ok(service.allProduct());
    }
    @GetMapping("/get/product/name")
    public ResponseEntity<ProductDto> getProductByName(@RequestParam String name){
        return ResponseEntity.ok(service.getProductByName(name));
    }
    @GetMapping("/get/products/category")
    public ResponseEntity<ProductDto> getProductByCategory(@RequestParam String category){
        return ResponseEntity.ok(service.getProductByCategory(category));
    }
}
