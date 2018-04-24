package com.example.android.mstu5031_knittingapp;

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

public class ItemActivity extends AppCompatActivity {

    private List<Item> items;
    private ItemAdapter stitchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        initialData();

        RecyclerView recylerView = (RecyclerView) findViewById(R.id.recycler_view);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter itemsAdapter = new ItemAdapter(items, this);
        recylerView.setAdapter(new ItemAdapter(items, this));
        recylerView.setAdapter(itemsAdapter);


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

    private Item getRandomItem() {
        int num = (int) (Math.random() * 2);
        if (num == 0)
            return new Item("Hat", "hat", R.drawable.hat);
        else if (num == 1)
            return new Item("Hat1", "hat1", R.drawable.hat1);
        else
            return new Item("Hat2", "hat2", R.drawable.hat2);
    }
}



