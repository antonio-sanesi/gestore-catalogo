package dev.anto.gestore.catalogo.service;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl (ProductRepository theProductRepository) {
        ProductRepository = theProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById (int theId) {
        Optional <Product> result = ProductRepository.findById(theId);

        Product theProduct = null;
            if (result.isPresent()) {
                theProduct = result.get();
            }
            else {
                throw new RunTimeException;
            }
            return theproduct;
    }

    @Override
    public Product save(Product theProduct) {
        productRepository.save(theProduct);
    }

    @Override
    void Product delete (int theId) {
        productRepository.delete(theProduct);
    }
}
