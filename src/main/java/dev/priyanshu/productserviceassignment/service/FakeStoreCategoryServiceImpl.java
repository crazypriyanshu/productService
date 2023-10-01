package dev.priyanshu.productserviceassignment.service;

import dev.priyanshu.productserviceassignment.dtos.FakeStoreProductDTO;
import dev.priyanshu.productserviceassignment.models.Category;
import dev.priyanshu.productserviceassignment.models.Product;

import java.util.ArrayList;
import java.util.List;

public class FakeStoreCategoryServiceImpl {
    public List<Category> getAllCategories() {
        List<FakeStoreProductDTO> fakeStoreProductDTO = fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO productDTO: fakeStoreProductDTO){
            products.add(convertFakeStoreProductDTOToProduct(productDTO));
        }
        return products;
    }

}
