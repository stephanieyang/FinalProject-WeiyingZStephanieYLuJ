package com.example.android.mstu5031_knittingapp;

public class Stitches {
    private String stitchesname;
    private int stitchesphoto;

    public Stitches(String stitchesname, int stitchesphoto){
        this.stitchesname = stitchesname;
        this.stitchesphoto = stitchesphoto;
    }

    public String getName() {
        return stitchesname;
    }

    public void setName(String name) {
        this.stitchesname = stitchesname;
    }

    public int getImageId() {
        return stitchesphoto;
    }

    public void setImageId(int imageId) {
        this.stitchesphoto = stitchesphoto;
    }
}

