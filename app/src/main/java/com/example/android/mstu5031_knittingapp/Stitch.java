package com.example.android.mstu5031_knittingapp;

public class Stitch {
    public String name0;
    public String image_name0;
    public String directions;
    public int imageId0;

//    public Stitch() {
//        this.name = "foo";
//        this.image_name = "foo_img";
//        this.directions = "foo_dir";
//
//    }

    public Stitch(String name, String image_name, String directions, int imageId){
        this.name0 = name;
        this.image_name0 = image_name;
        this.directions = directions;
        this.imageId0=imageId;
    }

    public String getImage_name() {
        return image_name0;
    }

    public int getImageId() {
        return imageId0;
    }

    public String getName() {
        return name0;
    }

    public void setName(String name) {
        this.name0 = name;
    }

    public String getImageName() {
        return image_name0;
    }

    public void setImageId(String image_name) {
        this.image_name0 = image_name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) { this.directions = directions; }
}

