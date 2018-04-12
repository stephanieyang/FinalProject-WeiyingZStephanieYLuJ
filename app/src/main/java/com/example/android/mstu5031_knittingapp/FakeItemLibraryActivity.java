package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FakeItemLibraryActivity extends AppCompatActivity {
    boolean otherPicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_item_library);

        Intent intent = getIntent();
        otherPicked = intent.getBooleanExtra(Keys.OTHER_PICKED,false);
    }

    public void chooseStitch(View view) {
        Intent intent = new Intent(this, FakeStitchLibraryActivity.class);
        startActivity(intent);
    }

    public void itemPicked(View view) {
        if(otherPicked) {
            Intent intent = new Intent(this, LibraryViewActivity.class);
            intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_CREATED);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FakeStitchLibraryActivity.class);
            intent.putExtra(Keys.OTHER_PICKED,true);
            startActivity(intent);
        }
    }
}
