package dev.anto.gestore.catalogo.rest;


@Controller
@RequestMappaing("/api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public EmployeeRestController(ProductService theProductService) {
        productService = theProductService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public Product singleProduct(@PathVariable int productId) {
        Product theProduct = productService.findById(productId);
        if (theProduct == null) {
            throw new RunTimeException;
        }
        return theProduct;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product theProduct) {
        theProduct.setId(0);
        Product gestore-catalogo = productService.save(theProduct);
            return gestore-catalogo;
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product theProduct) {
        Product gestore-catalogo = productService.save(theProduct);
            return gestore-catalogo;
    }

    @DeleteMapping("/product/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        Product deletingProduct = productService.findById(productId);
        if (deletingProduct == null) {
            throw new RunTimeException;
        }
        productService.deleteById(productId);
            return "Deleted Product - " + productId;
    }
}
