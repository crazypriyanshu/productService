package dev.priyanshu.productserviceassignment.service;

import dev.priyanshu.productserviceassignment.dtos.ProductDTO;
import dev.priyanshu.productserviceassignment.models.Category;
import dev.priyanshu.productserviceassignment.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductServiceImpl implements ProductServiceInterface{
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;

    }
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate =  restTemplateBuilder.build();

        ResponseEntity<ProductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class, productId);
        ProductDTO productDTO = response.getBody();
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        product.setCategory(category);
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImage_Url(product.getImage_Url());
        return product;
        }
    }



