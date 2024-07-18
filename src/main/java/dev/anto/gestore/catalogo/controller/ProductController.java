package dev.anto.gestore.catalogo.controller;


import dev.anto.gestore.catalogo.entity.Product;
import dev.anto.gestore.catalogo.service.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public Product singleProduct(@PathVariable int productId) {
        return productService.findById(productId);
    }

    @PostMapping("")
    public Product addProduct(@Valid @RequestBody Product theProduct) {
        theProduct.setId(null);
        return productService.save(theProduct);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(
            @PathVariable Integer productId,
            @Valid @RequestBody Product theProduct
    ) {
        theProduct.setId(productId);
        System.out.println(theProduct.getId());
        return productService.save(theProduct);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteById(productId);
            return "Deleted Product - " + productId;
    }
}
