package com.example.productservice.service.impl;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
  private final ProductRepository repo;
  private final ModelMapper mapper;

  public ProductServiceImpl(ProductRepository repo, ModelMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  @Override
  public ProductDTO create(ProductDTO dto) {
    if (repo.existsBySku(dto.getSku())) {
      throw new IllegalArgumentException("SKU already exists");
    }
    Product p = mapper.map(dto, Product.class);
    Product saved = repo.save(p);
    return mapper.map(saved, ProductDTO.class);
  }

  @Override
  public ProductDTO update(Long id, ProductDTO dto) {
    Product p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    p.setName(dto.getName());
    p.setDescription(dto.getDescription());
    p.setPrice(dto.getPrice());
    p.setQuantity(dto.getQuantity());
    // don't change sku on update in this example
    Product saved = repo.save(p);
    return mapper.map(saved, ProductDTO.class);
  }

  @Override
  public ProductDTO getById(Long id) {
    Product p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    return mapper.map(p, ProductDTO.class);
  }

  @Override
  public ProductDTO getBySku(String sku) {
    Product p = repo.findBySku(sku).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    return mapper.map(p, ProductDTO.class);
  }

  @Override
  public List<ProductDTO> listAll() {
    return repo.findAll().stream()
      .map(p -> mapper.map(p, ProductDTO.class))
      .collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    repo.deleteById(id);
  }
}
