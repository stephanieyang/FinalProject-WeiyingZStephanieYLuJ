package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Stephanie on 4/12/2018.
 */


public class StitchesViewHolder extends RecyclerView.ViewHolder{
    public CardView cardView;
    public TextView stitchesname;
    public ImageView stitchesPhoto;

    public StitchesViewHolder (View itemView, final Context context){
        super (itemView);
        cardView = (CardView)itemView.findViewById(R.id.card_view);
        stitchesname = (TextView)itemView.findViewById(R.id.text);

    }

}

