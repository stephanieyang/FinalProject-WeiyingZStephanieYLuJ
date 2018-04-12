package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    public void chooseStitch(View view) {
        // in the full version, pull up a recycler view activity with stitch cards to choose
        // here, we have a single dummy item that just transitions to the next page

        Intent intent = new Intent(this, FakeStitchLibraryActivity.class);
        intent.putExtra(Keys.OTHER_PICKED,false);
        startActivity(intent);
    }
    public void chooseItem(View view) {
        // in the full version, pull up a recycler view activity with stitch cards to choose
        // here, we have a single dummy item that just transitions to the next page

        Intent intent = new Intent(this, FakeItemLibraryActivity.class);
        intent.putExtra(Keys.OTHER_PICKED,false);
        startActivity(intent);
    }
}
