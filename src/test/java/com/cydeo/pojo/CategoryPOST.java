package com.cydeo.pojo;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class CategoryPOST {

    private String name;
    private String image;

    /*
    public CategoryPOST() {
        this.name = new Faker().name().firstName();
        this.image = new Faker().avatar().image();
    }
    */

}
