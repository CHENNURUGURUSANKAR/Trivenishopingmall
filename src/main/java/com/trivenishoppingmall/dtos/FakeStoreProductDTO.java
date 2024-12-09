package com.trivenishoppingmall.dtos;

import lombok.Getter;
import lombok.Setter;

/*
* DTO data transfer object
*
* */
@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private RatingDTO rating;
}
