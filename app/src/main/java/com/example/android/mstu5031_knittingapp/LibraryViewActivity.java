package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LibraryViewActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private List<KnitLibrary> patterns;
    private LibraryAdapter LibraryAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_view);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initialData();

        RecyclerView recylerView = (RecyclerView) findViewById(R.id.recycler_view);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(this));

        LibraryAdapter=new LibraryAdapter(patterns, this);
        recylerView.setAdapter(new LibraryAdapter(patterns, this));
        recylerView.setAdapter(LibraryAdapter);



        Intent intent = getIntent();
        String status = intent.getStringExtra(Keys.PAIR_STATUS);
        if(status == null) return;
        switch(status) {
            case Keys.PAIR_CREATED:
                Toast.makeText(this, R.string.create_notice_text, Toast.LENGTH_SHORT).show();
                break;
            case Keys.PAIR_SAVED:
                Toast.makeText(this, R.string.save_notice_text, Toast.LENGTH_SHORT).show();
                break;
            case Keys.PAIR_DELETED:
                Toast.makeText(this, R.string.delete_notice_text, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void initialData() {
        patterns = new ArrayList<>();
        patterns.add(new KnitLibrary("hat", R.drawable.hat1,R.drawable.twist_zigzag));
        patterns.add(new KnitLibrary("scarf", R.drawable.scarf,R.drawable.vine_lace));



        // TODO: REMOVE PLACEHOLDER AND ADD REAL VARIABLE CODE
        DatabaseReference pairRef = database.getReference("users/fake_user_key/matches");

        final ArrayList<UserCreatedPair> allPairsList = new ArrayList<UserCreatedPair>();

        Log.v("V","starting read");
        // Read from the database
        pairRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    UserCreatedPair currentPair = singleSnapshot.getValue(UserCreatedPair.class);
                    allPairsList.add(currentPair);

                }
                Log.d("V", "Size of list is: " + allPairsList.size());
                Log.d("V", allPairsList.get(0).getName() + " " + allPairsList.get(1).getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("V", "Failed to read value.", error.toException());
            }
        });
//        Query query = pairRef.orderByChild("email").equalTo("this");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // ...
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        
        ArrayList<UserCreatedPair> doneList = new ArrayList<UserCreatedPair>();
        ArrayList<UserCreatedPair> undoneList = new ArrayList<UserCreatedPair>();

        for(UserCreatedPair pair : allPairsList) {
            if(pair.isIs_done()) {
                doneList.add(pair);
            } else {
                undoneList.add(pair);
            }
        }

        /* Now you have:
         * allPairsList - list of all matches a user has made
         * doneList - all matches a user has marked as done
         * undoneList - all matches a user has NOT marked as done
         * (so doneList and undoneList combined together has the same contents as allPairsList)
         */

    }

    public void viewPairInfo (View view) {
        Intent intent = new Intent(this, ViewPairActivity.class);
        intent.putExtra(Keys.USER_ID, "fake_user_key"); // TODO: GENERATE THIS FOR REAL
        intent.putExtra(Keys.PAIR_ID, "fake_match_key_1"); // TODO: GENERATE THIS FOR REAL
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        //Intent intent = new Intent(this, ViewPairActivity.class);
        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }

    // TODO:
    // when a stitch/item pair's card is selected, open up a ViewPairActivity for that pair
    // (will want to pass the ID of that pair via intent)


}

