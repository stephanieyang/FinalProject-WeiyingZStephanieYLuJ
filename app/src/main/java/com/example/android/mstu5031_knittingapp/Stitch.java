package com.example.android.mstu5031_knittingapp;

public class Stitch {
    public String name;
    public String image_name;
    public String directions;

//    public Stitch() {
//        this.name = "foo";
//        this.image_name = "foo_img";
//        this.directions = "foo_dir";
//
//    }

    public Stitch(String name, String image_name, String directions) {
        this.name = name;
        this.image_name = image_name;
        this.directions = directions;
    }

    public String getImage_name() {
        return image_name;
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

    public static int getDrawableId(String imgName) {
        switch(imgName) {
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

    public static String imgNameToFullName(String imgName) {
        switch(imgName) {
            case "chevron":
                return "Chevron";
            case "crossed_loop":
                return "";
            case "daisy":
                return "";
            case "diamond_lattice":
                return "";
            case "faux_braid":
                return "";
            case "garter":
                return "";
            case "horseshoe":
                return "";
            case "japanese_feather":
                return "";
            case "king_charles_brocade":
                return "";
            case "long_slip_textured":
                return "";
            case "old_shale":
                return "";
            case "parallelogram":
                return "";
            case "pie_crust_basketweave":
                return "";
            case "rib1":
                return "";
            case "rib2":
                return "";
            case "scroll_lace":
                return "";
            case "seed":
                return "";
            case "slipped_hourglass":
                return "";
            case "stockinette":
                return "";
            case "twist_zigzag":
                return "";
            case "vine_lace":
                return "";
            case "woven_transverse_herringbone":
                return "";
            default:
                return ""; // shouldn't happen
        }
    }
}

