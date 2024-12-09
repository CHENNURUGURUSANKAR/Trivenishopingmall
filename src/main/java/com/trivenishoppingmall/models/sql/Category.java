package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="category")
public class Category {
    @Id
    private String title;
}
