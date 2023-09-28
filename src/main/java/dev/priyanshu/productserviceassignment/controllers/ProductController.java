package dev.priyanshu.productserviceassignment.controllers;

import dev.priyanshu.productserviceassignment.models.Product;
import dev.priyanshu.productserviceassignment.service.FakeStoreProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final FakeStoreProductServiceImpl fakeStoreProductService;


    public ProductController(FakeStoreProductServiceImpl fakeStoreProductService) {
        this.fakeStoreProductService = fakeStoreProductService;
    }

    // Get product
    @GetMapping("/{productId}")
    ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(
                "my-auth-token", "Priyanshu"
        );

        ResponseEntity<Product> response = new ResponseEntity<>(
                fakeStoreProductService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );
        return response;

    }
}
