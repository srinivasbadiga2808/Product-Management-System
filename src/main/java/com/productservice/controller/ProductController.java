package com.example.productservice.controller;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
    ProductDTO created = service.create(dto);
    return ResponseEntity.created(URI.create("/api/v1/products/" + created.getId())).body(created);
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> list() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @GetMapping("/sku/{sku}")
  public ResponseEntity<ProductDTO> getBySku(@PathVariable String sku) {
    return ResponseEntity.ok(service.getBySku(sku));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
