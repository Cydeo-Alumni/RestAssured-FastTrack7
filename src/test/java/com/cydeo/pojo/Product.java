package com.cydeo.pojo;

import lombok.Data;



import java.util.List;

@Data
public class Product {

    private int id;
    private String title;
    private int price;

    private List<String> allImages;

    private Category category;
}
