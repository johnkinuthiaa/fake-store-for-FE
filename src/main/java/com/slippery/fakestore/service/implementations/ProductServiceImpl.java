package com.slippery.fakestore.service.implementations;

import com.slippery.fakestore.dto.ProductDto;
import com.slippery.fakestore.models.Product;
import com.slippery.fakestore.repository.ProductRepository;
import com.slippery.fakestore.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        response.setProduct(product);
        response.setMessage("New product created successfully");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto updateProduct(Product product) {
        return null;
    }

    @Override
    public ProductDto findProductById(Long id) {
        ProductDto response =new ProductDto();
        Optional<Product> product =repository.findById(id);
        if(product.isEmpty()){
            response.setMessage("Product does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Product with id "+id);
        response.setProduct(product.get());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto deleteProductById(Long id) {
        ProductDto response =new ProductDto();
        Optional<Product> product =repository.findById(id);
        if(product.isEmpty()){
            response.setMessage("Product does not exist");
            response.setStatusCode(404);
            return response;
        }
        repository.deleteById(id);
        response.setMessage("With id "+id+" deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto allProduct() {
        ProductDto response =new ProductDto();
        List<Product> products =repository.findAll();
        if(products.isEmpty()){
            response.setMessage("Product list is empty");
            response.setStatusCode(404);
            return response;
        }
        response.setProducts(products);
        response.setMessage("All products");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto getProductByName(String name) {
        ProductDto response =new ProductDto();
        var withName =repository.findAll().stream()
                .filter(product -> product.getTitle().toLowerCase().contains(name.toLowerCase()))
                .toList();
        if(withName.isEmpty()){
            response.setMessage("No product with the name "+ name+" was found");
            response.setStatusCode(404);
            return response;
        }
        response.setProducts(withName);
        response.setMessage("Items with the name "+name);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto getProductByCategory(String category) {
        ProductDto response =new ProductDto();
        var withCategory =repository.findAll().stream()
                .filter(product -> product.getCategory().toLowerCase().contains(category.toLowerCase()))
                .toList();
        if(withCategory.isEmpty()){
            response.setMessage("No product from category "+ category+" was found");
            response.setStatusCode(404);
            return response;
        }
        response.setProducts(withCategory);
        response.setMessage("Items with the category  "+category);
        response.setStatusCode(200);
        return response;
    }
}
