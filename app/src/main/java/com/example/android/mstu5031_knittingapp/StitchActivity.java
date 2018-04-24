package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StitchActivity extends AppCompatActivity {

    private List<Stitch> stitches;
    private StitchAdapter stitchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitch);

        initialData();

        RecyclerView recylerView = (RecyclerView) findViewById(R.id.recycler_view);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        stitchesAdapter=new StitchAdapter(stitches, this);
        recylerView.setAdapter(new StitchAdapter(stitches, this));
        recylerView.setAdapter(stitchesAdapter);


    }


    private void initialData() {
        stitches = new ArrayList<>();
        stitches.add(new Stitch("Daisy", "","",R.drawable.daisy));
        stitches.add(new Stitch("Chevron", "","",R.drawable.chevron));
        stitches.add(new Stitch("Horseshoe","","", R.drawable.horseshoe));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.add:
               stitches.add(getRandomStitch());
               stitchesAdapter.notifyDataSetChanged();

           default:
               return super.onOptionsItemSelected(item);


           case R.id.save:
               Toast.makeText(this, "Your file is saved", Toast.LENGTH_SHORT).show();
               return true;
//            default:
//                return super.onOptionsItemSelected(item);
       }
   }

    private Stitch getRandomStitch() {
        int num = (int) (Math.random() * 2);
        if (num == 0)
            return new Stitch("Daisy", "daisy", "", R.drawable.daisy);
        else if (num == 1)
            return new Stitch("Chevron", "", "", R.drawable.chevron);
        else
            return new Stitch("Horseshoe", "", "", R.drawable.horseshoe);
    }
}




