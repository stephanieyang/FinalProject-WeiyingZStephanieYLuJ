package com.example.android.mstu5031_knittingapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Log.v("TESTING","here in CreateActivity onCreate");
    }

    public void chooseStitch(View view) {
        // in the full version, pull up a recycler view activity with stitch cards to choose
        // here, we have a single dummy item that just transitions to the next page
        Log.v("V","chooseStitch");
        Intent intent = new Intent(this, FakeStitchLibraryActivity.class);
        intent.putExtra(Keys.OTHER_PICKED,false);
        startActivity(intent);
    }
//    public void chooseItem(View view) {
////         in the full version, pull up a recycler view activity with stitch cards to choose
////         here, we have a single dummy item that just transitions to the next page
//
//        Intent intent = new Intent(this, FakeItemLibraryActivity.class);
//        intent.putExtra(Keys.OTHER_PICKED,false);
//        startActivity(intent);
//    }
    public void pickItem(View view){
        Intent pickItem=new Intent(this,ItemActivity.class);
        pickItem.putExtra(Keys.OTHER_PICKED,false);
       // pickItem.putExtra("title", ((EditText) findViewById(R.id.title)).getText().toString());

        startActivity(pickItem);
    }
    public void pickStitch(View view){
        Intent pickStitch=new Intent(this,StitchActivity.class);
        pickStitch.putExtra(Keys.OTHER_PICKED,false);
        // pickItem.putExtra("title", ((EditText) findViewById(R.id.title)).getText().toString());
        Log.v("TESTING","here in pickStitch");

        startActivity(pickStitch);
    }

}

