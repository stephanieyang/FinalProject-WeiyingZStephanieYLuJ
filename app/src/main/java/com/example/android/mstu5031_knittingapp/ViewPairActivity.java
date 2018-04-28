package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewPairActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String pairId;
    String userId;
    UserCreatedPair currentPair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pair);
        Log.v("TESTING","here in ViewPairActivity onCreate");


        Intent intent = getIntent();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        pairId = intent.getStringExtra(Keys.PAIR_ID);
        Log.v("TESTING","Pair ID = " + pairId);


        Log.v("TESTING","trying to get database ref");

        DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);


        Log.v("TESTING","got database ref");

        if(pairId.equals("TEST_ID")) {
            currentPair = new UserCreatedPair(false,"0001","hat","twist_zigzag","cool hat","for me","");
            Log.v("TESTING", "Name of pair is: " +  currentPair.getName());
        } else {


            Log.v("TESTING", "starting read");
            // Read from the database
            pairRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    currentPair = dataSnapshot.getValue(UserCreatedPair.class);
                    Log.d("V", "Name of pair is: " + currentPair.getName());
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("V", "Failed to read value.", error.toException());
                }
            });

        }

        // TODO: display information from the viewedPair object
        setDisplayInfo();
    }

    void setDisplayInfo() {
        Log.v("TESTING","start of setDisplayInfo");
        TextView nameView = (TextView) findViewById(R.id.pair_name_text);
        nameView.setText(currentPair.getName());
        TextView notesView = (TextView) findViewById(R.id.pair_notes_text);
        notesView.setText(currentPair.getNotes());
        Log.v("TESTING","setting images in setDisplayInfo");
        ImageView stitchImgView = (ImageView) findViewById(R.id.pickedPattern);
        stitchImgView.setImageResource(Stitch.getDrawableId(currentPair.getStitch()));
        Log.v("TESTING","successfully set stitch image");
        ImageView itemImgView = (ImageView) findViewById(R.id.ItemPicked);
        itemImgView.setImageResource(Item.getDrawableId(currentPair.getItem()));
        Log.v("TESTING","end of setDisplayInfo");
    }

    public void editPairInfo(View view) {
        Intent intent = new Intent(this, EditPairActivity.class);
        intent.putExtra(Keys.PAIR_ID, pairId);
        intent.putExtra(Keys.USER_ID, userId);
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        if(userId == "" || pairId == "" || pairId == "TEST_ID") return; // for testing
        DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
        pairRef.removeValue();
        // if the pair ID is empty, then this pair hasn't actually been created so we can't really delete
        // so just go to the library

        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }
}
