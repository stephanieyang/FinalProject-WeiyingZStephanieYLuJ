package com.example.android.mstu5031_knittingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        // get image data
        Intent intent = getIntent();
        String imageByteString = intent.getStringExtra(Keys.VIEW_PHOTO);
        ImageView photoView = (ImageView)findViewById(R.id.viewed_photo);

        // convert and set image data
        Bitmap imageBitmap = ImageUtil.byteStringToBitmap(imageByteString);
        photoView.setImageBitmap(imageBitmap);
    }
}
