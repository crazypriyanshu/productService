package dev.priyanshu.productserviceassignment.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewProductDTO {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
