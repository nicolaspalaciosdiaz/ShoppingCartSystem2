package com.example.ShoppingCartSystem2.service;

import com.example.ShoppingCartSystem2.model.Product;
import com.example.ShoppingCartSystem2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    public List<Product> findAllProducts(){
        return repository.findAll();
    }

    public Optional<Product> findProductById(String id){
        return repository.findById(id);
    }

    public Product save(Product product){
        return repository.save(product);
    }

    public void delete(String id){
        repository.deleteById(id);
    }
}
