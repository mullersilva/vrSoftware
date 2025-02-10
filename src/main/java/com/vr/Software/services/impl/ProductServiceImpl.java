package com.vr.Software.services.impl;

import com.vr.Software.entities.ProductEntity;
import com.vr.Software.repositories.ProductRepository;
import com.vr.Software.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> listAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public void deleteByCodigo(Long codigo) {
        productRepository.deleteByCodigoNative(codigo);
    }

    @Override
    public ProductEntity persistProduct(ProductEntity product) {
        return productRepository.save(product);
    }
}
