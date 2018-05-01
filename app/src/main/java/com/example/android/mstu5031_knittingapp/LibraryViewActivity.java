package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class LibraryViewActivity extends AppCompatActivity {


    private TextView mTextMessage;
    private List<UserCreatedPair> patterns;
    private LibraryAdapter LibraryAdapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private RecyclerView recyclerView;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference userReference;

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

        //auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    userReference = database.getReference(user.getUid());
                    auth = FirebaseAuth.getInstance();
                } else {
                    startActivity(new Intent(LibraryViewActivity.this, LoginActivity.class));
                }
            }
        };


        mTextMessage = (TextView) findViewById(R.id.message);
        //   BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //   navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initialData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Context context = this;

        //LibraryAdapter=new LibraryAdapter(patterns, this);
        //recylerView.setAdapter(LibraryAdapter);


        // handle pair status messages
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



    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }


    private void initialData() {
        patterns = new ArrayList<>();
        patterns.add(new UserCreatedPair(false,"0001","hat","twist_zigzag","cool hat","for me",""));
        patterns.add(new UserCreatedPair(false,"0002","scarf","vine_lace","yellow scarf","for me",""));
        Log.v("TESTING","end of filler initalize");

        String userId = auth.getCurrentUser().getUid();
        Log.v("TESTING","got user id");
        DatabaseReference pairRef = database.getReference("users/" + userId + "/matches");
        final Context context = this;

        final ArrayList<UserCreatedPair> allPairsList = new ArrayList<UserCreatedPair>();

        Log.v("TESTING","starting read");
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
                if(allPairsList.size() > 1) {
                    Log.d("V", allPairsList.get(0).getName() + " " + allPairsList.get(1).getName());
                }
                patterns = allPairsList;
                recyclerView.setAdapter(new LibraryAdapter(patterns, context));
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
        ViewGroup group = (ViewGroup) view.getParent();
        TextView textView;
        boolean found = false;
        String pairId = "";
        for (int i = 0; i < group.getChildCount(); i++) {
            View currentView = group.getChildAt(i);
            if (currentView instanceof TextView) {
                textView = (TextView) currentView;
                if(textView.getId() == R.id.id_text) {
                    Log.v("TESTING","id = " + textView.getText().toString());
                    found = true;
                    pairId = textView.getText().toString();
                    break;
                }
            }
        }
        if(!found) {
            Log.v("TESTING","no ID found in LibraryViewActivity viewPairInfo");
        }


        Intent intent = new Intent(this, ViewPairActivity.class);
        intent.putExtra(Keys.PAIR_ID, pairId);
        Log.v("TESTING","LibraryViewActivity: Starting intent");
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.newPro:

                Intent a=new Intent(this,CreateActivity.class);
                startActivity(a);
                return true;

            default:
                return super.onOptionsItemSelected(item);

            case R.id.log:
                logOut();
                Intent i=new Intent(this,LoginActivity.class);
                startActivity(i);
                return true;

            //    default:
            //         return super.onOptionsItemSelected(item);
        }
    }


    public void logOut(){
        auth.signOut();
    }


}

