package com.trivenishoppingmall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    @Id
    private String title;
}
