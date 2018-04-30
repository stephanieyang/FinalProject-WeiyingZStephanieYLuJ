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
import android.widget.ImageView;
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
    private RecyclerView recylerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        prevIntent = getIntent();


        initialData();

        recylerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        recylerView1.setHasFixedSize(true);
        recylerView1.setLayoutManager(new LinearLayoutManager(this));


//        String itemName = items.get(0).getImage_name();
//        Intent mrIntent = new Intent(this, EditPairActivity.class);
//        mrIntent.putExtra(Keys.ITEM_NAME, itemName);
//        startActivity(mrIntent);

    }


    private void initialData() {
        items = new ArrayList<>();
        items.add(new Item("Hat", "hat"));
        items.add(new Item("Hat1", "hat1"));
        items.add(new Item("Hat2", "hat2"));



        final ArrayList<Item> itemList = new ArrayList<Item>();

        DatabaseReference itemLibRef = database.getReference("items");
        Log.v("V","starting read");
        // Read from the database
        itemLibRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    Item currentItem = (Item)singleSnapshot.getValue(Item.class);
                    itemList.add(currentItem);

                }
                Log.d("V", "Size of list is: " + itemList.size());
                Log.d("V", itemList.get(0).getName() + " " + itemList.get(1).getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("V", "Failed to read value.", error.toException());
            }
        });
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
            return new Item("Hat", "hat");
        else if (num == 1)
            return new Item("Hat1", "hat1");
        else
            return new Item("Hat2", "hat2");
    }

    public void chooseItem(View view) {
        Log.v("TESTING", "here in chooseItem");
        ViewGroup group = (ViewGroup) view.getParent();
        TextView textView;
        for (int i = 0; i < group.getChildCount(); i++) {
            View currentView = group.getChildAt(i);
            if (currentView instanceof TextView) {
                textView = (TextView) currentView; //Found it!
                Log.v("TESTING", textView.getText().toString());
                String itemImgName = Item.getImgNameFromName(textView.getText().toString());
                Log.v("TESTING","calling chooseItemWithSelection with item " + itemImgName);
                chooseItemWithSelection(itemImgName);
                return;
            }
        }
        Log.v("TESTING","Error: no item found");

    }

    /*
     * For when a user chooses some item.
     * Determines whether to progress to stitch selection (if user hasn't selected a stitch) or the library (if user has).
     */
    private void chooseItemWithSelection(String itemImgName) {
        Log.v("TESTING","start of chooseItemWithSelection");

        boolean otherPicked = prevIntent.getBooleanExtra(Keys.OTHER_PICKED,false);
        Log.v("TESTING","got otherPicked");
        if(otherPicked) {
            Log.v("TESTING","otherPicked = true");
            Intent intent = new Intent(this, EditPairActivity.class);
            String stitchImgName = prevIntent.getStringExtra(Keys.STITCH_NAME);

            intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_CREATED);
            // add in stitch/item component info; rest gets filled in on the edit screen
            intent.putExtra(Keys.STITCH_NAME, stitchImgName);
            intent.putExtra(Keys.ITEM_NAME, itemImgName);
            // don't need to put in a pair ID here, since one hasn't been created yet
            startActivity(intent);
        } else {
            Log.v("TESTING","otherPicked = false");
            Intent intent = new Intent(this, StitchActivity.class);
            intent.putExtra(Keys.OTHER_PICKED,true);
            intent.putExtra(Keys.ITEM_NAME, itemImgName);
            startActivity(intent);
        }
    }
}



