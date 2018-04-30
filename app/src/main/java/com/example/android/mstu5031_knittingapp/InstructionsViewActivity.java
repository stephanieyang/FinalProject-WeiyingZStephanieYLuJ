package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class InstructionsViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_view);
        Log.v("TESTING","start of InstructionsViewActivity onCreate");
        // get data
        Intent intent = getIntent();
        String stitchDirections = intent.getStringExtra(Keys.STITCH_DIRECTIONS);
        // set stitch instructions for display
        TextView dirDisplay = (TextView)findViewById(R.id.stitch_directions);
        dirDisplay.setText(stitchDirections);
    }
}
