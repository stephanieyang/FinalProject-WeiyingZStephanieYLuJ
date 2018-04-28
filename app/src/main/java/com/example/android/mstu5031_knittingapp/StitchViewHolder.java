package com.example.android.mstu5031_knittingapp;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Stephanie on 4/12/2018.
 */

public class StitchViewHolder extends RecyclerView.ViewHolder{

    public CardView cardView;
    public TextView stitchName;
    public ImageView stitchPhoto;

    public StitchViewHolder(View itemView,final Context context) {
        super(itemView);
        cardView=(CardView)itemView.findViewById(R.id.card_view1);
        stitchName=(TextView)itemView.findViewById(R.id.text1);
        stitchPhoto=(ImageView)itemView.findViewById(R.id.image1);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,stitchName.getText(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}




