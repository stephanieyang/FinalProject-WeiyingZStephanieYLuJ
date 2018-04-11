package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewPairActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pair);
    }

    public void editPairInfo(View view) {
        Intent intent = new Intent(this, EditPairActivity.class);
        startActivity(intent);
    }
}
