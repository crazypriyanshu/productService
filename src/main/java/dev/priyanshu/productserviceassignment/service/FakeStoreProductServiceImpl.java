package dev.priyanshu.productserviceassignment.service;

import dev.priyanshu.productserviceassignment.clients.fakeStoreAPI.FakeStoreClient;
import dev.priyanshu.productserviceassignment.dtos.AddNewProductDTO;
import dev.priyanshu.productserviceassignment.dtos.FakeStoreProductDTO;
import dev.priyanshu.productserviceassignment.dtos.ProductDTO;
import dev.priyanshu.productserviceassignment.models.BaseModel;
import dev.priyanshu.productserviceassignment.models.Category;
import dev.priyanshu.productserviceassignment.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductServiceInterface {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;

    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T>responseType, Object... uriVariables ) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    // I want to return Product for now, so I will convert FakeStoreProductDto to product
    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO productDTO) {
        Product product = new Product();
        // set 5 fields
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setCreatedAt(productDTO.getCreatedAt());
        product.setImage(productDTO.getImage());

        // setup category
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }




    public List<Product> getAllProducts() {
        List<FakeStoreProductDTO> fakeStoreProductDTO = fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO productDTO: fakeStoreProductDTO){
            products.add(convertFakeStoreProductDTOToProduct(productDTO));
        }
        return products;
    }

    public Product addNewProduct(AddNewProductDTO addNewProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                addNewProductDTO,
                FakeStoreProductDTO.class
        );
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }

    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        FakeStoreProductDTO fakeStoreProductDTOResponse = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDTO,
                FakeStoreProductDTO.class,
                productId
        );
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTOResponse);


    }

    public Product replaceProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();

        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDTO,
                FakeStoreProductDTO.class,
                productId
        );
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTOResponseEntity.getBody());
    };

}



