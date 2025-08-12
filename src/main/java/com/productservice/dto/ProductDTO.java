package com.example.productservice.dto;

import lombok.*;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
  private Long id;

  @NotBlank
  private String sku;

  @NotBlank
  private String name;

  private String description;

  @NotNull
  @DecimalMin("0.0")
  private Double price;

  @NotNull
  @Min(0)
  private Integer quantity;
}
