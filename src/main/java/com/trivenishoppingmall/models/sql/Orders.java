package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="orders")
public class Orders{
    @Id
    private Long id;
    private Long ProductId;
    private String status;
}
