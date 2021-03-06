package com.example.ShoppingCartSystem2.controller;

import com.example.ShoppingCartSystem2.model.Product;
import com.example.ShoppingCartSystem2.repository.ProductRepository;
import com.example.ShoppingCartSystem2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    //@Autowired
    //private ProductRepository repository;
    // Kopplar till mit repo.

    private List<Product> getProducts(){
        List <Product> products = new ArrayList<>();

        products.add(new Product("1", "Coffee", 49, 1));
        products.add(new Product("2", "Shampoo", 22, 1));
        products.add(new Product("3", "Apple", 4, 1));
        products.add(new Product("4", "Banana", 5, 1));
        products.add(new Product("5", "Milk", 13, 1));
        products.add(new Product("6", "Spaghetti", 18, 1));
        products.add(new Product("7", "Toilet paper", 42, 1));
        products.add(new Product("8", "Jos", 24, 1));

        return products;
    }

    public int calculateTotal() {
        List <Product> productsInCart = service.findAllProducts();
        int total = 0;
        for (Product product: productsInCart) {
            total += product.getPrice() * product.getAmount();
        }

        return total;
    }

    @GetMapping("/store")
    public String listProductsInStore(Model model) {
        model.addAttribute("store", getProducts());
        return "store";
    }

    @GetMapping("/")
    public String listProductsInRoot(Model model) {
        model.addAttribute("store", getProducts());
        return "store";
    }

    @GetMapping("/addProduct/{id}")
    public String addProduct(@PathVariable("id") String id) {

        if (service.findProductById(id).isPresent()) { // Om produkten redan finns (s??ker p?? id)...
            Product product = service.findProductById(id).get();
            product.setAmount(product.getAmount() + 1); // ...??kas antalet med 1
            service.save(product); // ...och uppdateras i databasen.
        } else { // Annars...
            List<Product> store = getProducts();
            for (Product product: store) { // ...hittar vi produkten vi valt med r??tt id fr??n listan
                if (product.id.equals(id)) {
                    service.save(product); // ...och l??gger till i databasen.
                }
            }
        }
        return "redirect:/store";
    }
    // L??gger till producterna i databasen! "Create"

    @GetMapping("/cart")
    public String getProductsFromCart(Model model) {
        model.addAttribute("cart", service.findAllProducts());
        model.addAttribute("total", calculateTotal());
        return "cart";
    }
    // Hittar produkterna i databasen! "Read"

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable (value = "id") String id) {
        service.delete(id);
        return "redirect:/cart";
    }
    // Tar bort fr??n databasen "Delete"

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable (value = "id") String id) {
        if (service.findProductById(id).isPresent()) {
            Product product = service.findProductById(id).get();
            product.setAmount(product.getAmount() - 1);
            if (product.getAmount() < 1) {
                service.delete(id);
            } else {
                service.save(product);
            }
        }
        return "redirect:/cart";
    }
    @GetMapping("/increase/{id}")
    public String increase(@PathVariable (value = "id") String id) {
        if (service.findProductById(id).isPresent()) {
            Product product = service.findProductById(id).get();
            product.setAmount(product.getAmount() + 1);

            service.save(product);
        }
        return "redirect:/cart";
    }
}
