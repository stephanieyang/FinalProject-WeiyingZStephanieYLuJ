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

import java.sql.Ref;
import java.util.ArrayList;

public class ViewPairActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String pairId;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pair);


        Intent intent = getIntent();
        String userId = intent.getStringExtra(Keys.USER_ID);
        String pairId = intent.getStringExtra(Keys.PAIR_ID);

        DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);



        Log.v("V","starting read");
        // Read from the database
        pairRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserCreatedPair viewedPair = dataSnapshot.getValue(UserCreatedPair.class);
                Log.d("V", "Name of pair is: " + viewedPair.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("V", "Failed to read value.", error.toException());
            }
        });

        // TODO: display information from the viewedPair object
    }

    public void editPairInfo(View view) {
        Intent intent = new Intent(this, EditPairActivity.class);
        intent.putExtra(Keys.PAIR_ID, pairId);
        intent.putExtra(Keys.USER_ID, userId);
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        if(userId == "" || pairId == "") return; // for testing
        DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
        pairRef.removeValue();
        // if the pair ID is empty, then this pair hasn't actually been created so we can't really delete
        // so just go to the library

        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }
}
