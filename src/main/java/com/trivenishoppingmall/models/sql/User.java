package com.trivenishoppingmall.models.sql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private Date createdAt;
    private Date modifiedAt;
    @OneToMany
    private List<Address>  addresses;
    @OneToMany
    private List<Orders> orders;

}
