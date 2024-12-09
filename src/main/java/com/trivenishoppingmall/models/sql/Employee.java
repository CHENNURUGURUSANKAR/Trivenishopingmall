package com.trivenishoppingmall.models.sql;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity(name="employee")
@Getter
@Setter
public class Employee extends User{
    private Date loginAt;
}
