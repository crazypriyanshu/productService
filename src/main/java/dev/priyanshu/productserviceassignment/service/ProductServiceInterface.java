package dev.priyanshu.productserviceassignment.service;

import dev.priyanshu.productserviceassignment.dtos.AddNewProductDTO;
import dev.priyanshu.productserviceassignment.dtos.FakeStoreProductDTO;
import dev.priyanshu.productserviceassignment.dtos.ProductDTO;
import dev.priyanshu.productserviceassignment.models.Product;

import java.util.List;

public interface ProductServiceInterface {

    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product addNewProduct(AddNewProductDTO addNewProductDTO);
    Product updateProduct(Long prouctId, Product product);

    Product replaceProduct(Long productId, Product product);

}
