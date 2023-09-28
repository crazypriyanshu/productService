package dev.priyanshu.productserviceassignment.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
