package com.trivenishoppingmall.services;

import com.trivenishoppingmall.dtos.FakeStoreProductDTO;
import com.trivenishoppingmall.models.Category;
import com.trivenishoppingmall.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDTO fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);
        return convertToProduct(fakeStoreProduct);
    }

    @Override
    public List<Product> getAllProductsById() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    private static Product convertToProduct(FakeStoreProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        Category category = new Category();
        category.setTitle(dto.getCategory());  // Assuming the category title is the same as the category string from DTO.
        product.setCategory(category);

        return product;
    }
}
