package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="product")
public class Product {
    @Id
    private int id;
    @ManyToMany
    private List<Category> category;

}
