package com.trivenishoppingmall.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String title, description;
    private double price;
    private Category category;

}
