package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class LibraryActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private List<UserCreatedPair> patterns;
    private LibraryAdapter libraryAdapter;

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
      //  BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
      //  navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initialData();

        RecyclerView recylerView = (RecyclerView) findViewById(R.id.recycler_view);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(this));

        libraryAdapter=new LibraryAdapter(patterns, this);
        recylerView.setAdapter(new LibraryAdapter(patterns, this));
        recylerView.setAdapter(libraryAdapter);


    }


    private void initialData() {
        patterns = new ArrayList<>();
        patterns.add(new UserCreatedPair(false,"0001","hat","twist_zigzag","cool hat","for me",""));
        patterns.add(new UserCreatedPair(false,"0002","scarf","vine_lace","yellow scarf","for me",""));
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
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
        }
    }

}



