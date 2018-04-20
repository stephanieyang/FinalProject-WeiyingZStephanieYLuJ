package com.example.android.mstu5031_knittingapp;

/**
 * Created by Stephanie on 4/20/2018.
 */

public class Item {

    private String name;
    private String image_name;

    public Item() {
        this.name = "foo";
        this.image_name = "foo_img";
    }

    public Item(String name, String image_name) {
        this.name = name;
        this.image_name = image_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

}
