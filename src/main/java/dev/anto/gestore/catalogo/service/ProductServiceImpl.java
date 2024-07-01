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
        return productRepository.findById(theId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto mancante")
        );
    }

    @Override
    public Product save(Product theProduct) {
        //controllo che la descrizione sia okay
        if (theProduct.getDescription() == null
                || theProduct.getDescription().trim().length() < 3
                || theProduct.getDescription().length() > 200
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrizione prodotto non valida");
        }

        //controllo che il nome sia okay
        if (theProduct.getName() == null
                || theProduct.getName().trim().length() < 3
                || theProduct.getName().length() > 50
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome prodotto non valido");
        }

        //controllo che il prezzo sia okay
        if (theProduct.getPrice() == null
                || theProduct.getPrice() <= 0
                || theProduct.getPrice() >= 999
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prezzo prodotto non valido");
        }

        return productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        var daCancellare = this.findById(theId);
        productRepository.delete(daCancellare);
    }

}
