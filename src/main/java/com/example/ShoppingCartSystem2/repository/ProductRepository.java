package com.example.ShoppingCartSystem2.repository;

import com.example.ShoppingCartSystem2.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
