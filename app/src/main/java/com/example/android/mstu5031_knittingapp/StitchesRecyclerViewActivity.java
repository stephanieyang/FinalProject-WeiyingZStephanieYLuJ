package com.example.android.mstu5031_knittingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;

/**
 * Created by Lulu on 2018/4/12.
 */

public class StitchesRecyclerViewActivity extends AppCompatActivity {

    ArrayList<Stitches> stitches;
    Adapter stitchesadapter;
    RecyclerView stitchesrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitches_recycler_view);

        initialData();

        stitchesrecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        stitchesrecyclerView.setHasFixedSize(true);
        stitchesrecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        stitchesadapter = new StitchesAdapter(stitches, this);
//        stitchesrecyclerView.setAdapter(stitchesadapter);

    }

    private void initialData() {
        stitches = new ArrayList<>();
        stitches.add(new Stitches("stockinette", R.drawable.stockinette));
        stitches.add(new Stitches("garter",  R.drawable.garter));
        stitches.add(new Stitches("rib1", R.drawable.rib1));
    }

    public void addRandomStitches(View view){
        stitches.add(getRandomStitches());
       // stitchesadapter.notifyDataSetChanged();
    }

    private Stitches getRandomStitches() {
        int num = (int) (Math.random() * 4);
        if (num == 0)
            return new Stitches("SAMPLE1", R.drawable.stockinette);
        else if (num == 1)
            return new Stitches("SAMPLE2",  R.drawable.garter);
        else
            return new Stitches("SAMPLE3", R.drawable.rib1);
    }
}


