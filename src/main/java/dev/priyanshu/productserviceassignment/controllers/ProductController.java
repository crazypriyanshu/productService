package dev.priyanshu.productserviceassignment.controllers;

import dev.priyanshu.productserviceassignment.dtos.AddNewProductDTO;
import dev.priyanshu.productserviceassignment.dtos.FakeStoreProductDTO;
import dev.priyanshu.productserviceassignment.dtos.ProductDTO;
import dev.priyanshu.productserviceassignment.models.Category;
import dev.priyanshu.productserviceassignment.models.Product;
import dev.priyanshu.productserviceassignment.service.FakeStoreProductServiceImpl;
import dev.priyanshu.productserviceassignment.service.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductServiceInterface productService;


    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    // Get product
    @GetMapping("/{productId}")
    ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(
                "my-auth-token", "Priyanshu"
        );

        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );
        return response;

    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody AddNewProductDTO addNewProductDTO) {
        Product newProduct = productService.addNewProduct(addNewProductDTO);
        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(productId);
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
//        product.setImage(fakeStoreProductDTO.getImage());

        return productService.updateProduct(productId, product);
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId, @RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(productId);
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());

        return productService.replaceProduct(productId, product);
    }


}
