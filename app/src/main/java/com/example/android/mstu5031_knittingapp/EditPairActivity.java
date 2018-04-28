package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class EditPairActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String userId;
    String pairId;
    String stitchImgName;
    String itemImgName;
    boolean pairExists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pair);

        Log.v("TESTING", "in EditPairActivity onCreate");

        Intent intent = getIntent();
        //userId = intent.getStringExtra(Keys.USER_ID);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        pairId = intent.getStringExtra(Keys.PAIR_ID);
        pairExists = !(pairId == null || pairId.equals("null") || pairId.length() == 0); // true if pairId is non-empty, false if it's empty

        final Context context = this;

        Log.v("TESTING", "figuring out pair info...");

        if(!pairExists) {
            Log.v("TESTING", "pair does not exist");
            stitchImgName = intent.getStringExtra(Keys.STITCH_NAME);
            itemImgName = intent.getStringExtra(Keys.ITEM_NAME);
        } else {
            Log.v("TESTING","pairID = " + pairId);
            // read info from database
            DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            final String loadFailureText = this.getResources().getString(R.string.load_failure_text);
            //UserCreatedPair currentPair;

            // read stitch info
            // Read from the database
            pairRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    final UserCreatedPair currentPair = dataSnapshot.getValue(UserCreatedPair.class);
                    Log.d("V", "Read pair is: " + currentPair.getName());

                    DatabaseReference stitchRef = database.getReference("stitches/" + currentPair.getStitch());
                    final DatabaseReference itemRef = database.getReference("items/" + currentPair.getItem());

                    stitchRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            final Stitch currentStitch = dataSnapshot.getValue(Stitch.class);
                            Log.d("V", "Read stitch is: " + currentStitch.getName());
                            itemRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    Item currentItem = dataSnapshot.getValue(Item.class);
                                    Log.d("V", "Read pair is: " + currentItem.getName());


                                    // with all data loaded, modify the values onscreen
                                    ((ImageView)findViewById(R.id.pair_item_img)).setImageResource(Item.getDrawableId(currentItem.getName()));
                                    ((TextView)findViewById(R.id.pair_item_name)).setText("");
                                    ((ImageView)findViewById(R.id.pair_stitch_img)).setImageResource(Item.getDrawableId(currentStitch.getName()));
                                    ((TextView)findViewById(R.id.pair_stitch_name)).setText("");
                                    ((EditText)findViewById(R.id.pair_name_entry)).setText(currentPair.getName());
                                    ((EditText)findViewById(R.id.pair_notes_entry)).setText(currentPair.getNotes());
                                    ((CheckBox)findViewById(R.id.pair_done_entry)).setChecked(currentPair.is_done);
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w("V", "Failed to read value.", error.toException());
                                    Toast.makeText(context, loadFailureText, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("V", "Failed to read value.", error.toException());
                            Toast.makeText(context, loadFailureText, Toast.LENGTH_SHORT).show();
                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("V", "Failed to read value.", error.toException());
                    Toast.makeText(context, loadFailureText, Toast.LENGTH_SHORT).show();
                }
            });
        }

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
            DatabaseReference currentPairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            UserCreatedPair currentPair = new UserCreatedPair(isDone, pairId, itemImgName, stitchImgName, pairName, pairNotes, "");
            currentPairRef.setValue(currentPair);
            // TODO: add code for photo upload
        } else {
            String newPairId = UUID.randomUUID().toString();
            String newPairName = ((EditText) findViewById(R.id.pair_name_entry)).getText().toString();
            String newPairNotes = ((EditText) findViewById(R.id.pair_notes_entry)).getText().toString();
            boolean isDone = ((CheckBox) findViewById(R.id.pair_done_entry)).isChecked();
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
        if(pairExists) {
            DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);
            pairRef.removeValue();
        }
        // if the pair ID is empty, then this pair hasn't actually been created so we can't really delete
        // so just go to the library

        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }
}
