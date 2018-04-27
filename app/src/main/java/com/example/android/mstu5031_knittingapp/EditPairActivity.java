package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class EditPairActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //String userId;
    String pairId;
    String stitchImgName;
    String itemImgName;
    boolean pairExists = (pairId != "");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pair);

        Intent intent = getIntent();
        //userId = intent.getStringExtra(Keys.USER_ID);
        pairId = intent.getStringExtra(Keys.PAIR_ID);

        if(!pairExists) {
            stitchImgName = intent.getStringExtra(Keys.STITCH_NAME);
            itemImgName = intent.getStringExtra(Keys.ITEM_NAME);
        } else {
            // TODO: read info from database
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
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();
        if(pairExists) {
            String pairName = ((EditText) findViewById(R.id.pair_name_entry)).getText().toString();
            String pairNotes = ((EditText) findViewById(R.id.pair_notes_entry)).getText().toString();
            boolean isDone = ((CheckBox) findViewById(R.id.pair_done)).isChecked();
            // TODO: add code to edit already existing pair
        } else {
            String newPairId = UUID.randomUUID().toString();
            String newPairName = ((EditText) findViewById(R.id.pair_name_entry)).getText().toString();
            String newPairNotes = ((EditText) findViewById(R.id.pair_notes_entry)).getText().toString();
            boolean isDone = ((CheckBox) findViewById(R.id.pair_done)).isChecked();
            UserCreatedPair dummyPair = new UserCreatedPair(isDone, newPairId, itemImgName, stitchImgName, newPairName, newPairNotes,"");
            DatabaseReference newPairRef = database.getReference("users/" + userId + "/matches/" + newPairId);
            newPairRef.setValue(dummyPair);
        }

        //Intent intent = new Intent(this, ViewPairActivity.class);
        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_SAVED);
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();
        if(pairExists) {
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
