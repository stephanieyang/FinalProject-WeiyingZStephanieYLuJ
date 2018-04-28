package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by weiyingzhu on 18/4/2.
 */

public class LibraryViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView patternName;
    public ImageView patternPhoto;
    Context context;


    public LibraryViewHolder(View itemView, final Context context) {
        super(itemView);
        cardView=(CardView)itemView.findViewById(R.id.card_view_library);
        patternName=(TextView)itemView.findViewById(R.id.text);
        patternPhoto=(ImageView)itemView.findViewById(R.id.image);
        this.context = context;


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



    }

    public void viewLibraryItem() {
        Toast.makeText(context,patternName.getText(), Toast.LENGTH_SHORT).show();
    }



}

