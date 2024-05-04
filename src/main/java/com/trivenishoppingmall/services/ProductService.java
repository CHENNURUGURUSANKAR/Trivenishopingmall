package com.trivenishoppingmall.services;

import com.trivenishoppingmall.models.Product;

import java.util.List;

public interface ProductService {
    // get product
    Product getProductById(Long id);
    // get all products
    List<Product> getAllProductsById();

    // create product
    Product createProduct(Product product);
    //replace product
    Product replaceProduct(Product product,Long id);
    //update product
    Product updateProduct(Product product,Long id);
    // delete product
    Product deleteProduct(Long id);



}
