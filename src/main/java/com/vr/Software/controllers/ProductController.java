package com.vr.Software.controllers;

import com.vr.Software.entities.ProductEntity;
import com.vr.Software.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vr-api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/get-all-products", produces = "application/json")
    public List<ProductEntity> listAllProducts() {
        return productService.listAllProducts();
    }

    @DeleteMapping(path = "/delete-product/{codigo}", produces = "application/json")
    public void deleteProduct(@PathVariable Long codigo) {
        productService.deleteByCodigo(codigo);
    }

    @PostMapping(path = "/persist-product", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductEntity> persistProduct(@Valid @RequestBody ProductEntity product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.persistProduct(product));
    }
}
