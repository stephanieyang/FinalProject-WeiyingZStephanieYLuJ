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

    public static int getDrawableId(String name) {
        switch(name) {
            case "chevron":
                return R.drawable.chevron;
            case "crossed_loop_cable":
                return R.drawable.crossed_loop_cable;
            case "daisy":
                return R.drawable.daisy;
            case "diamond_lattice":
                return R.drawable.diamond_lattice;
            case "faux_braid":
                return R.drawable.faux_braid;
            case "garter":
                return R.drawable.garter;
            case "horseshoe":
                return R.drawable.horseshoe;
            case "japanese_feather":
                return R.drawable.japanese_feather;
            case "king_charles_brocade":
                return R.drawable.king_charles_brocade;
            case "long_slip_textured":
                return R.drawable.long_slip_textured;
            case "old_shale":
                return R.drawable.old_shale;
            case "parallelogram":
                return R.drawable.parallelogram;
            case "pie_crust_basketweave":
                return R.drawable.pie_crust_basketweave;
            case "rib1":
                return R.drawable.rib1;
            case "rib2":
                return R.drawable.rib2;
            case "scroll_lace":
                return R.drawable.scroll_lace;
            case "seed":
                return R.drawable.seed;
            case "slipped_hourglass":
                return R.drawable.slipped_hourglass;
            case "stockinette":
                return R.drawable.stockinette;
            case "twist_zigzag":
                return R.drawable.twist_zigzag;
            case "vine_lace":
                return R.drawable.vine_lace;
            case "woven_transverse_herringbone":
                return R.drawable.woven_transverse_herringbone;
            default:
                return 0; // shouldn't happen
        }
    }
}

