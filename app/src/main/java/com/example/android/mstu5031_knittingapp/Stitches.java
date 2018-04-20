package com.example.android.mstu5031_knittingapp;

public class Stitches {
    private String name;
    private String image_name;
    private String directions;

    public Stitches() {
        this.name = "foo";
        this.image_name = "foo_img";
        this.directions = "foo_dir";
    }

    public Stitches(String name, String image_name, String directions){
        this.name = name;
        this.image_name = image_name;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return image_name;
    }

    public void setImageId(String image_name) {
        this.image_name = image_name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) { this.directions = directions; }
}

