package com.trivenishoppingmall.models;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Product {
    private Long id;
    private String title, description;
    private double price;
    private Category category;
    private List<String> imgList;

}
