package com.example.android.mstu5031_knittingapp;

/**
 * Created by weiyingzhu on 18/4/21.
 */

public class Item {

        public String name;
        public String image_name;

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



    /*
     * Convenience method for turning image names -> resource IDs
     */
    public static int getDrawableIdFromImgName(String name) {
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

    /*
     * Convenience method for turning names -> img names
     */
    public static String getImgNameFromName(String name) {
        switch(name) {
            case "Hat (puff)":
                return "hat1";
            case "Hat (beanie)":
                return "hat2";
            case "Hat":
                return "hat";
            case "Hat1":
                return "hat1";
            case "Hat2":
                return "hat2";
            case "Scarf":
                return "scarf";
            case "Gloves":
                return "gloves";
            case "Socks":
                return "socks";
            case "Sweater":
                return "sweater";
            default:
                return ""; // shouldn't happen
        }
    }

    }

