package com.vr.Software.services;

import com.vr.Software.entities.ProductEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductEntity> listAllProducts();
    public void deleteByCodigo(Long codigo);
    public ProductEntity persistProduct(ProductEntity product);
}
