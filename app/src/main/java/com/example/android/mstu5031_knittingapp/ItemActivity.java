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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {
    Intent prevIntent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private List<Item> items;
    private ItemAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        initialData();

        RecyclerView recylerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        recylerView1.setHasFixedSize(true);
        recylerView1.setLayoutManager(new LinearLayoutManager(this));

        itemsAdapter = new ItemAdapter(items, this);
        recylerView1.setAdapter(new ItemAdapter(items, this));
        recylerView1.setAdapter(itemsAdapter);


        String itemName= (String) ((TextView) findViewById(R.id.text)).getText();
        Intent mrIntent = new Intent(this, EditPairActivity.class);
        mrIntent.putExtra(Keys.ITEM_NAME, itemName);
        startActivity(mrIntent);

    }


    private void initialData() {
        items = new ArrayList<>();
        items.add(new Item("Hat", "hat", R.drawable.hat));
        items.add(new Item("Hat1", "hat1", R.drawable.hat1));
        items.add(new Item("Hat2", "hat2",R.drawable.hat2));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                items.add(getRandomItem());
                itemsAdapter.notifyDataSetChanged();

            default:
                return super.onOptionsItemSelected(item);


            case R.id.save:
                Toast.makeText(this, "Your file is saved", Toast.LENGTH_SHORT).show();
                return true;
//            default:
//                return super.onOptionsItemSelected(item);
        }
    }

    private Item getRandomItem() {
        int num = (int) (Math.random() * 2);
        if (num == 0)
            return new Item("Hat", "hat", R.drawable.hat);
        else if (num == 1)
            return new Item("Hat1", "hat1", R.drawable.hat1);
        else
            return new Item("Hat2", "hat2", R.drawable.hat2);
    }

    /*
     * For when a user chooses some item.
     * Determines whether to progress to stitch selection (if user hasn't selected a stitch) or the library (if user has).
     */
    public void chooseItem(String itemImgName) {
        boolean otherPicked = prevIntent.getBooleanExtra(Keys.OTHER_PICKED,false);
        if(otherPicked) {
            Intent intent = new Intent(this, EditPairActivity.class);
            String stitchImgName = intent.getStringExtra(Keys.STITCH_NAME);
            intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_CREATED);
            // add in stitch/item component info; rest gets filled in on the edit screen
            intent.putExtra(Keys.STITCH_NAME, stitchImgName);
            intent.putExtra(Keys.ITEM_NAME, itemImgName);
            // don't need to put in a pair ID here, since one hasn't been created yet
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, StitchActivity.class);
            intent.putExtra(Keys.OTHER_PICKED,true);
            intent.putExtra(Keys.STITCH_NAME, itemImgName);
            startActivity(intent);
        }
    }
}



