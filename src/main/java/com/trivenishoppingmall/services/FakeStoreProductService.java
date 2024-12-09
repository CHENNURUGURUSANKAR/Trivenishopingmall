package com.trivenishoppingmall.services;

import com.trivenishoppingmall.dtos.FakeStoreProductDTO;
import com.trivenishoppingmall.models.Category;
import com.trivenishoppingmall.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // completed
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDTO fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);
        return convertToProduct(fakeStoreProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        // to avoid linkedHashmap use array[]
        FakeStoreProductDTO[] fakeStoreProductsList = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        List<Product> productList = new ArrayList<>();

        assert fakeStoreProductsList != null;
        for (FakeStoreProductDTO fakeStoreProductDTO:fakeStoreProductsList){
             productList.add(convertToProduct(fakeStoreProductDTO));
        }
        return productList;
    }

    // 04th may
    @Override
    public Product createProduct(Product product) {

        FakeStoreProductDTO fakeStoreProduct = convertToFakeStoreProduct(product);
        FakeStoreProductDTO responseFakeStoreProduct = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDTO.class);
        return convertToProduct(responseFakeStoreProduct);
    }

    private FakeStoreProductDTO convertToFakeStoreProduct(Product product) {
        if (product == null) {
            return null;
        }
        // image
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getTitle());

        return fakeStoreProductDTO;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDTO fakeStoreProductDTO=convertToFakeStoreProduct(product);
        restTemplate.put("https://fakestoreapi.com/products/"+id,fakeStoreProductDTO,FakeStoreProductDTO.class);

        return convertToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        FakeStoreProductDTO fakeStoreProductDTO=convertToFakeStoreProduct(product);
       FakeStoreProductDTO responseFakeStoreProductDTO= restTemplate.patchForObject("https://fakestoreapi.com/products/"+id,fakeStoreProductDTO,FakeStoreProductDTO.class);
        return convertToProduct(responseFakeStoreProductDTO);
    }

    // 04th may
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
