package dev.anto.gestore.catalogo.service.interfaces;

import dev.anto.gestore.catalogo.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(int theId);

    Product save(Product theProduct);

    void deleteById(int theId);

}
