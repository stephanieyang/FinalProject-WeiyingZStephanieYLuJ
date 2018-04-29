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
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StitchActivity extends AppCompatActivity {
    Intent prevIntent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private List<Stitch> stitches = new ArrayList<>();
    private StitchAdapter stitchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitch);
        Log.v("TESTING","here in onCreate");

        prevIntent = getIntent();

        initialData();

        RecyclerView recylerView = (RecyclerView) findViewById(R.id.recycler_view3);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        stitchesAdapter=new StitchAdapter(stitches, this);
        recylerView.setAdapter(new StitchAdapter(stitches, this));
        recylerView.setAdapter(stitchesAdapter);

    }


    private void initialData() {
        Log.v("TESTING","here in initialData");
        stitches.add(new Stitch("Daisy", "daisy",""));
        stitches.add(new Stitch("Chevron", "chevron",""));
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
            return new Stitch("Daisy", "daisy", "");
        else if (num == 1)
            return new Stitch("Chevron", "", "");
        else
            return new Stitch("Horseshoe", "", "");
    }



    public void chooseStitch(View view) {
        Log.v("TESTING", "here in chooseStitch");
        ViewGroup group = (ViewGroup) view.getParent();
        TextView textView;
        for (int i = 0; i < group.getChildCount(); i++) {
            View currentView = group.getChildAt(i);
            if (currentView instanceof TextView) {
                textView = (TextView) currentView;
                Log.v("TESTING", textView.getText().toString());
                String stitchImgName = Stitch.getImgNameFromName(textView.getText().toString());
                Log.v("TESTING","calling chooseStitchWithSelection");
                chooseStitchWithSelection(stitchImgName);
                return;
            }
        }
        Log.v("TESTING","Error: no stitch found");

    }

    /*
     * For when a user chooses some item.
     * Determines whether to progress to stitch selection (if user hasn't selected a stitch) or the library (if user has).
     */
    private void chooseStitchWithSelection(String stitchImgName) {
        Log.v("TESTING","start of chooseStitchWithSelection");

        boolean otherPicked = prevIntent.getBooleanExtra(Keys.OTHER_PICKED,false);
        Log.v("TESTING","got otherPicked");
        if(otherPicked) {
            Log.v("TESTING","otherPicked = true");
            Intent intent = new Intent(this, EditPairActivity.class);
            String itemImgName = intent.getStringExtra(Keys.ITEM_NAME);

            intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_CREATED);
            // add in stitch/item component info; rest gets filled in on the edit screen
            intent.putExtra(Keys.STITCH_NAME, stitchImgName);
            intent.putExtra(Keys.ITEM_NAME, itemImgName);
            // don't need to put in a pair ID here, since one hasn't been created yet
            startActivity(intent);
        } else {
            Log.v("TESTING","otherPicked = false");
            Intent intent = new Intent(this, ItemActivity.class);
            intent.putExtra(Keys.OTHER_PICKED,true);
            intent.putExtra(Keys.STITCH_NAME, stitchImgName);
            startActivity(intent);
        }
    }
}




