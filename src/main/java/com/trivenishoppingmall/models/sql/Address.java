package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="address")
public class Address {
    @Id
    private long id;
    private String village;
    private String landmark;
    private String location;
    private int pincode;

}
