package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class EditPairActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String userId;
    String pairId;
    String stitchImgName;
    String itemImgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pair);

        Intent intent = getIntent();
        userId = intent.getStringExtra(Keys.USER_ID);
        pairId = intent.getStringExtra(Keys.PAIR_ID);

        if(pairId == "") {
            stitchImgName = intent.getStringExtra(Keys.STITCH_NAME);
            itemImgName = intent.getStringExtra(Keys.ITEM_NAME);
        }

        // TODO: display stitch & item images on page based on what was passed via intent

//        String placeholderStitchVariable_deleteMeLater = "stockinette";
//        String placeholderItemVariable_deleteMeLater = "mittens";
//
//        DatabaseReference stitchRef = database.getReference(placeholderStitchVariable_deleteMeLater);
//        DatabaseReference itemRef = database.getReference(placeholderStitchVariable_deleteMeLater);
//
//        // read stitch info
//        // Read from the database
//        stitchRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("V", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("V", "Failed to read value.", error.toException());
//            }
//        });
//
//        // read item info
//        // Read from the database
//        itemRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("V", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("V", "Failed to read value.", error.toException());
//            }
//        });
    }

    public void savePairInfo(View view) {
        // TODO: get user ID information
        String testUserId = "user123";
        // TODO: create a real new pair based on user input
        String newPairId = UUID.randomUUID().toString();
        UserCreatedPair dummyPair = new UserCreatedPair(false, newPairId, "foo_item","foo_stitch","foo_name","foo_notes","");
        DatabaseReference newPairRef = database.getReference("users/" + testUserId + "/matches/" + newPairId);
        newPairRef.setValue(dummyPair);

        //Intent intent = new Intent(this, ViewPairActivity.class);
        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_SAVED);
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        if(pairId != "") {
            DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            pairRef.setValue("");
        }
        // if the pair ID is empty, then this pair hasn't actually been created so we can't really delete
        // so just go to the library

        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }
}
