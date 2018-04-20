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

        final ArrayList<Item> itemList = new ArrayList<Item>();

        DatabaseReference stitchLibRef = database.getReference("items");
        Log.v("V","starting read");
        // Read from the database
        stitchLibRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    Item currentItem = singleSnapshot.getValue(Item.class);
                    itemList.add(currentItem);

                }
                Log.d("V", "Size of list is: " + itemList.size());
                Log.d("V", itemList.get(0).getName() + " " + itemList.get(1).getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("V", "Failed to read value.", error.toException());
            }
        });
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
