package com.trivenishoppingmall.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.trivenishoppingmall.models.Category;
import com.trivenishoppingmall.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Primary
public class FirebaseProductService implements ProductService {

    String COLLECTION = "products";

    @Override
    public Product getProductById(Long id) {
        Firestore firestore = FirestoreClient.getFirestore();
        Product product = new Product();
        try {
            DocumentReference docRef = firestore.collection(COLLECTION).document(String.valueOf(id));

            ApiFuture<DocumentSnapshot> future = docRef.get();
            // Block until the future is complete and get the document snapshot
            DocumentSnapshot documentSnapshot = future.get();

            // Check if the document exists
            if (documentSnapshot.exists()) {
                // Convert the document snapshot to a Product object
                product = convertFirebaseProductToProduct(documentSnapshot);
                // Do something with the product object
            } else {
                System.out.println("Document does not exist!");
            }
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
            System.err.println("Error getting document: " + e.getMessage());
        }
        return product;
    }

    private Product convertFirebaseProductToProduct(DocumentSnapshot documentSnapshot) {
        Product product = new Product();
        try {
            Long id = documentSnapshot.contains("id") ? (Long) documentSnapshot.get("id") : 0;
            product.setId(id);
            product.setTitle(documentSnapshot.getString("title"));
            product.setDescription(documentSnapshot.getString("description"));
            product.setPrice((double) documentSnapshot.get("price"));
            Category category = new Category();
            category.setTitle(documentSnapshot.getString("category"));
            product.setCategory(category);
            product.setImgList((List<String>) documentSnapshot.get("imgList"));
            return product;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Product();
        }
    }

    private Product convertFirebaseProductToProduct(QueryDocumentSnapshot queryDocumentSnapshot) {
        Product product = new Product();
        try {
            Long id = queryDocumentSnapshot.contains("id") ? (Long) queryDocumentSnapshot.get("id") : 0;
            product.setId(id);
            product.setTitle(queryDocumentSnapshot.getString("title"));
            product.setDescription(queryDocumentSnapshot.getString("description"));
            product.setPrice((double) queryDocumentSnapshot.get("price"));
            Category category = new Category();
            category.setTitle(queryDocumentSnapshot.getString("category"));
            product.setCategory(category);
            return product;
        } catch (Exception e) {
            return new Product();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> snapshotApiFuture = firestore.collection(COLLECTION).limit(10).offset(0).get();
        Product product = new Product();
        try {
            QuerySnapshot querySnapshot = snapshotApiFuture.get(); // This blocks until the future is complete

            // Iterate through the documents in the QuerySnapshot
            for (QueryDocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                // System.out.println(documentSnapshot.toString());
                product = convertFirebaseProductToProduct(documentSnapshot);
                list.add(product);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Product createProduct(Product product) {
        Firestore firestore = FirestoreClient.getFirestore();
        Map<String, Object> productMap = convertToPoductMap(product);
        firestore.collection(COLLECTION).document(productMap.get("id") + "").create(productMap);
        return convertMapToProduct(productMap);

    }

    private Product convertMapToProduct(Map<String, Object> productMap) {
        Product product = new Product();
        product.setId((Long) productMap.get("id"));
        product.setTitle((String) productMap.get("title"));
        product.setDescription((String) productMap.get("description"));
        product.setPrice((Double) productMap.get("price"));
        product.setImgList((List<String>) productMap.get("imgList"));
        Category category = new Category();
        category.setTitle((String) productMap.get("category"));
        product.setCategory(category);
        return product;
    }

    private Map<String, Object> convertToPoductMap(Product product) {
        Map<String, Object> map = new HashMap<>();
        long randomLong = (long) (Math.random() * Long.MAX_VALUE);
        map.put("id", randomLong);
        map.put("title", product.getTitle());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());
        map.put("category", product.getCategory().getTitle());
        List<String> imgList=product.getImgList();
        map.put("imgList",imgList);

        return map;
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
}
