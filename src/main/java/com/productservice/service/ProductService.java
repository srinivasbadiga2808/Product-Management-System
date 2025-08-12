package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import java.util.List;

public interface ProductService {
  ProductDTO create(ProductDTO dto);
  ProductDTO update(Long id, ProductDTO dto);
  ProductDTO getById(Long id);
  ProductDTO getBySku(String sku);
  List<ProductDTO> listAll();
  void delete(Long id);
}
