package dev.priyanshu.productserviceassignment.dtos;

import dev.priyanshu.productserviceassignment.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDTO {
    private Product product;
}
