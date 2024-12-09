package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="sellers")
public class Seller extends User{
    private String shopName;
    private double avgRating;
    private Long numberOfSales;
    @OneToMany
    private List<Product> products;
}
