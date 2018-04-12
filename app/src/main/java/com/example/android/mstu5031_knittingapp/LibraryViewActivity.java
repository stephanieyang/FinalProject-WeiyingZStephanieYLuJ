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


import java.util.ArrayList;
import java.util.List;

public class LibraryViewActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private List<KnitLibrary> patterns;
    private LibraryAdapter LibraryAdapter;

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
        switch(status) {
            case Keys.PAIR_CREATED:
                Toast.makeText(this, R.string.create_text, Toast.LENGTH_SHORT).show();
                break;
            case Keys.PAIR_SAVED:
                Toast.makeText(this, R.string.save_text, Toast.LENGTH_SHORT).show();
                break;
            case Keys.PAIR_DELETED:
                Toast.makeText(this, R.string.delete_text, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void initialData() {
        patterns = new ArrayList<>();
        patterns.add(new KnitLibrary("hat", R.drawable.hat1,R.drawable.twist_zigzag));
        patterns.add(new KnitLibrary("scarf", R.drawable.scarf,R.drawable.vine_lace));
    }

    public void viewPairInfo (View view) {
        Intent intent = new Intent(this, ViewPairActivity.class);
        Log.v("testing","HERE IN LIBRARYVIEWACTIVITY");
        startActivity(intent);
    }

    public void deletePairInfo(View view) {
        //Intent intent = new Intent(this, ViewPairActivity.class);
        Intent intent = new Intent(this, LibraryViewActivity.class);
        intent.putExtra(Keys.PAIR_STATUS, Keys.PAIR_DELETED);
        startActivity(intent);
    }


}

