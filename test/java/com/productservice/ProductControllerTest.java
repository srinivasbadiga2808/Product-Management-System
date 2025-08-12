package com.example.productservice;

import com.example.productservice.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

  @Autowired
  private TestRestTemplate rest;

  @Test
  void createAndGetProduct() {
    ProductDTO dto = ProductDTO.builder()
            .sku("SKU-100")
            .name("Test Product")
            .description("desc")
            .price(12.5)
            .quantity(100)
            .build();

    ResponseEntity<ProductDTO> resp = rest.postForEntity("/api/v1/products", dto, ProductDTO.class);
    assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    ProductDTO created = resp.getBody();
    assertThat(created).isNotNull();
    assertThat(created.getId()).isNotNull();

    ResponseEntity<ProductDTO> getResp = rest.getForEntity("/api/v1/products/" + created.getId(), ProductDTO.class);
    assertThat(getResp.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(getResp.getBody().getSku()).isEqualTo("SKU-100");
  }
}
