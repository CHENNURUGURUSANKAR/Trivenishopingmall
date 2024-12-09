package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Entity(name="admin")
@Getter
@Setter
public class Admin extends User{
    private Date loginAt;

}
