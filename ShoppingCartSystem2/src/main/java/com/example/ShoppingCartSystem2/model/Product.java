package com.example.ShoppingCartSystem2.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString // kan den användas

@Document(collection = "Cart")
@Entity
public class Product {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // (Hämtar istället id:t från listan store när man lägger till i databasen)
    public String id;

    private String productName;

    private int price;

    private int amount;

    public Product(String productName, int price, int amount) {
        this.productName = productName;
        this.price = price;
        this.amount = amount;
    }
}
