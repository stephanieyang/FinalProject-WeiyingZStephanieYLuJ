package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FakeItemLibraryActivity extends AppCompatActivity {
    boolean otherPicked;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

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
