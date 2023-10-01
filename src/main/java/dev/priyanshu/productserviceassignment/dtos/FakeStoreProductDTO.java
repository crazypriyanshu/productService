package dev.priyanshu.productserviceassignment.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// this class should give me only those fields what i want to show to my clients
@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private LocalDateTime createdAt;
}
