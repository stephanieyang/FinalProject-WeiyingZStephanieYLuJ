package com.example.android.mstu5031_knittingapp;

/**
 * Created by weiyingzhu on 18/4/21.
 */

public class UserCreatedPair {

 
        boolean is_done;
        String id;
        String item;
        String stitch;
        String name;
        String notes;
        String user_photo;

        public UserCreatedPair() {
            this.is_done = false;
            this.item = "foo_item";
            this.stitch = "foo_stitch";
            this.name = "foo_name";
            this.notes = "foo_notes";
            this.user_photo = "foo_photo";
        }

        public UserCreatedPair(boolean is_done, String id, String item, String stitch, String name, String notes, String user_photo) {
            this.is_done = is_done;
            this.item = item;
            this.stitch = stitch;
            this.name = name;
            this.notes = notes;
            this.user_photo = user_photo;
        }

        public boolean isIs_done() { return is_done; }

        public void setIs_done(boolean is_done) {
            this.is_done = is_done;
        }

        public String getId() { return id; }

        public void setId(String id) { this.id = id; }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getStitch() {
            return stitch;
        }

        public void setStitch(String stitch) {
            this.stitch = stitch;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }
}
