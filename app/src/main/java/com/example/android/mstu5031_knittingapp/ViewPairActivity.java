package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
        pairId = intent.getStringExtra(Keys.PAIR_ID); // guaranteed to be something because you only get to this screen from the library
        Log.v("TESTING","Pair ID = " + pairId);
        final Context context = this;


        Log.v("TESTING","trying to get database ref");

        DatabaseReference pairRef = database.getReference("users/" + userId + "/matches/" + pairId);


        Log.v("TESTING","got database ref");

        if(pairId.equals("TEST_ID")) {
            currentPair = new UserCreatedPair(false,"0001","hat","twist_zigzag","cool hat","for me","");
            Log.v("TESTING", "Name of pair is: " +  currentPair.getName());
        } else {
            // getting information from server
            final String loadFailureText = this.getResources().getString(R.string.load_failure_text);

            // Read from the database
            pairRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final UserCreatedPair currentPair = dataSnapshot.getValue(UserCreatedPair.class);
                    Log.d("V", "Read pair is: " + currentPair.getName());


                    // because UserCreatedPair only holds stitch/item names, we have to read from the database again to get info for each
                    DatabaseReference stitchRef = database.getReference("stitches/" + currentPair.getStitch());
                    final DatabaseReference itemRef = database.getReference("items/" + currentPair.getItem());

                    stitchRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // get stitch data
                            final Stitch currentStitch = dataSnapshot.getValue(Stitch.class);
                            Log.d("V", "Read stitch is: " + currentStitch.getName());
                            itemRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // get item data
                                    Item currentItem = dataSnapshot.getValue(Item.class);
                                    Log.d("V", "Read pair is: " + currentItem.getName());


                                    // with all data loaded, modify the values onscreen
                                    ((ImageView)findViewById(R.id.ItemPicked)).setImageResource(Item.getDrawableIdFromImgName(currentItem.getName()));
                                    ((TextView)findViewById(R.id.pair_item_name)).setText("");
                                    ((ImageView)findViewById(R.id.pickedPattern)).setImageResource(Item.getDrawableIdFromImgName(currentStitch.getName()));
                                    ((TextView)findViewById(R.id.pair_stitch_name)).setText("");
                                    ((TextView)findViewById(R.id.pair_name_text)).setText(currentPair.getName());
                                    ((TextView)findViewById(R.id.pair_notes_text)).setText(currentPair.getNotes());
                                    ((CheckBox)findViewById(R.id.pair_done)).setChecked(currentPair.is_done);
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w("V", "Failed to read value.", error.toException());
                                    // error message toast
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
    }

    public void editPairInfo(View view) {
        Intent intent = new Intent(this, EditPairActivity.class);
        // intent.putExtra(Keys.PAIR_ID, pairId);
        // TODO: take the following block out when everything works
        if(!(pairId.contains("TEST") || pairId.contains("fake"))) {
            intent.putExtra(Keys.PAIR_ID, pairId);
        }
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        // TODO: take the following line out when everything works
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
