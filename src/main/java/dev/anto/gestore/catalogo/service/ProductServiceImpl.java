package dev.anto.gestore.catalogo.service;

import dev.anto.gestore.catalogo.entity.Product;
import dev.anto.gestore.catalogo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById (int theId) {
        return productRepository.findById(theId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "prodotto mancante"));
    }

    @Override
    public Product save(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        var daCancellare = this.findById(theId);
        productRepository.delete(daCancellare);
    }

}
