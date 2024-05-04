package com.trivenishoppingmall.controllers;

import com.trivenishoppingmall.models.Product;
import com.trivenishoppingmall.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 *  CRUD
 *  patch is to update field
 * put recreate product with put body
 * POST to create product
 *
 * */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProductById(@RequestBody Product product, @PathVariable("id") Long id) {
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable("id") Long idl) {
        return new Product();
    }

}
