package dev.anto.gestore.catalogo.controller;


import dev.anto.gestore.catalogo.entity.Product;
import dev.anto.gestore.catalogo.service.interfaces.ProductService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @RolesAllowed({ "ADMIN", "PRODM" })
    @PostMapping("")
    public Product addProduct(@Valid @RequestBody Product theProduct) {
        theProduct.setId(null);
        return productService.save(theProduct);
    }

    @RolesAllowed({ "ADMIN", "PRODM" })
    @PutMapping("/{productId}")
    public Product updateProduct(
            @PathVariable Integer productId,
            @Valid @RequestBody Product theProduct
    ) {
        theProduct.setId(productId);
        System.out.println(theProduct.getId());
        return productService.save(theProduct);
    }

    @RolesAllowed({ "ADMIN" })
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteById(productId);
            return "Deleted Product - " + productId;
    }
}
