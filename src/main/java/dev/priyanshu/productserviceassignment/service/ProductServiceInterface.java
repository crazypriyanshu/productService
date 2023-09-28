package dev.priyanshu.productserviceassignment.service;

import dev.priyanshu.productserviceassignment.models.Product;
import org.springframework.http.ResponseEntity;

public interface ProductServiceInterface {

    Product getSingleProduct(Long productId);

}
