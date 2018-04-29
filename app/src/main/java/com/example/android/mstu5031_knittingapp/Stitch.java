package com.example.android.mstu5031_knittingapp;

public class Stitch {
    public String name;
    public String image_name;
    public String directions;

    public Stitch() {
        this.name = "foo";
        this.image_name = "foo_img";
        this.directions = "foo_dir";

    }

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

    /*
     * Convenience method for turning image names -> resource IDs
     */
    public static int getDrawableIdFromImageName(String imgName) {
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


    /*
     * Convenience method for turning names -> image names
     */
    public static String getImgNameFromName(String imgName) {
        switch(imgName) {
            case "Chevron":
                return "chevron";
            case "Crossed Loop (cable)":
                return "crossed_loop_cable";
            case "Daisy":
                return "daisy";
            case "Diamond Lattice":
                return "diamond_lattice";
            case "Faux Braid":
                return "faux_braid";
            case "Garter":
                return "garter";
            case "Horseshoe":
                return "horseshoe";
            case "Japanese Feather":
                return "japanese_feather";
            case "King Charles Brocade":
                return "king_charles_brocade";
            case "long_slip_textured":
                return "Long-Slip Textured";
            case "Old Shale":
                return "old_shale";
            case "Parallelogram":
                return "parallelogram";
            case "Pie Crust Basketweave":
                return "pie_crust_basketweave";
            case "1x1 Rib":
                return "rib1";
            case "2x2 Rib":
                return "rib2";
            case "Scroll Lace":
                return "scroll_lace";
            case "Seed":
                return "seed";
            case "Slipped Hourglass":
                return "slipped_hourglass";
            case "Stockinette":
                return "stockinette";
            case "Twist Zigzag":
                return "twist_zigzag";
            case "Vine Lace":
                return "vine_lace";
            case "Woven Transverse Herringbone":
                return "woven_transverse_herringbone";
            default:
                return ""; // shouldn't happen
        }
    }
}

