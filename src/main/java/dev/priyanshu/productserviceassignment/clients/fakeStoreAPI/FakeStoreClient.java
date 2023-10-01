package dev.priyanshu.productserviceassignment.clients.fakeStoreAPI;

import dev.priyanshu.productserviceassignment.dtos.FakeStoreProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
        );
        return Arrays.asList(response.getBody());
    }
}
