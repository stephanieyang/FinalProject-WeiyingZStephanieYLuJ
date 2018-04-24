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

public class FakeStitchLibraryActivity extends AppCompatActivity {
    boolean otherPicked;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_stitch_library);

        Intent intent = getIntent();
        otherPicked = intent.getBooleanExtra(Keys.OTHER_PICKED,false);

        final ArrayList<Stitch> stitchList = new ArrayList<Stitch>();

        DatabaseReference stitchLibRef = database.getReference("stitches");
        Log.v("V","starting read");
        // Read from the database
        stitchLibRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    Stitch currentStitch = singleSnapshot.getValue(Stitch.class);
                    stitchList.add(currentStitch);

                }
                Log.d("V", "Size of list is: " + stitchList.size());
                Log.d("V", stitchList.get(0).getName() + " " + stitchList.get(1).getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("V", "Failed to read value.", error.toException());
            }
        });
    }

    public void stitchPicked(View view) {
        if(otherPicked) {
            Intent intent = new Intent(this, LibraryViewActivity.class);
            intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_CREATED);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FakeItemLibraryActivity.class);
            intent.putExtra(Keys.OTHER_PICKED,true);
            startActivity(intent);
        }
    }




}

