package com.example.android.mstu5031_knittingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Lulu on 2018/4/12.
 */

public class StitchesRecyclerViewActivity extends AppCompatActivity {

    ArrayList<Stitches> stitches;
    Adapter stitchesadapter;
    RecyclerView stitchesrecyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitches_recycler_view);

        initialData();

        stitchesrecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        stitchesrecyclerView.setHasFixedSize(true);
        stitchesrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<Stitches> stitchList = new ArrayList<Stitches>();

        DatabaseReference stitchLibRef = database.getReference("stitches");
        Log.v("V","starting read");
        // Read from the database
        stitchLibRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    Stitches currentStitch = singleSnapshot.getValue(Stitches.class);
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

//        stitchesadapter = new StitchesAdapter(stitches, this);
//        stitchesrecyclerView.setAdapter(stitchesadapter);

    }

    private void initialData() {
        stitches = new ArrayList<>();
        stitches.add(new Stitches("stockinette", "stockinette", ""));
        stitches.add(new Stitches("garter",  "garter", ""));
        stitches.add(new Stitches("rib1", "rib1", ""));
    }

    public void addRandomStitches(View view){
        stitches.add(getRandomStitches());
       // stitchesadapter.notifyDataSetChanged();
    }

    private Stitches getRandomStitches() {
        int num = (int) (Math.random() * 4);
        if (num == 0)
            return new Stitches("SAMPLE1", "stockinette", "");
        else if (num == 1)
            return new Stitches("SAMPLE2",  "garter", "");
        else
            return new Stitches("SAMPLE3", "rib1", "");
    }
}


