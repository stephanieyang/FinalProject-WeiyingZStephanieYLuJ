package com.example.android.mstu5031_knittingapp;

public class Stitch {
    public String name;
    public String image_name;
    public String directions;
    public int imageId;

//    public Stitch() {
//        this.name = "foo";
//        this.image_name = "foo_img";
//        this.directions = "foo_dir";
//
//    }

    public Stitch(String name, String image_name, String directions, int imageId){
        this.name = name;
        this.image_name = image_name;
        this.directions = directions;
        this.imageId=imageId;
    }

    public String getImage_name() {
        return image_name;
    }

    public int getImageId() {
        return imageId;
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

