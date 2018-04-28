package com.example.android.mstu5031_knittingapp;

/**
 * Created by weiyingzhu on 18/4/21.
 */

public class Item {

        public String name;
        public String image_name;
        public int imageId;

//        public Item() {
//            this.name = "foo";
//            this.image_name = "foo_img";
//        }

        public Item(String name, String image_name, int imageId) {
            this.name = name;
            this.image_name = image_name;
            this.imageId = imageId;
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

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }



    public static int getDrawableId(String name) {
        switch(name) {
            case "hat":
                return R.drawable.hat;
            case "hat1":
                return R.drawable.hat1;
            case "hat2":
                return R.drawable.hat2;
            case "scarf":
                return R.drawable.scarf;
            case "gloves":
                return R.drawable.gloves;
            case "socks":
                return R.drawable.socks;
            case "sweater":
                return R.drawable.sweater;
            default:
                return 0; // shouldn't happen
        }
    }

    }

