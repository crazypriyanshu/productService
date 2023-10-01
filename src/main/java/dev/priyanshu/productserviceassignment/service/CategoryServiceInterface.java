package dev.priyanshu.productserviceassignment.service;

import dev.priyanshu.productserviceassignment.models.Category;
import dev.priyanshu.productserviceassignment.models.Product;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAllCategories();
}
