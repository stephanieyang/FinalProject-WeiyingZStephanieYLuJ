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

public class ItemViewHolder extends RecyclerView.ViewHolder{

    public CardView cardView;
    public TextView itemName;
    public ImageView itemPhoto;

    public ItemViewHolder(View itemView,final Context context) {
        super(itemView);
        cardView=(CardView)itemView.findViewById(R.id.card_view);
        itemName=(TextView)itemView.findViewById(R.id.text);
        itemPhoto=(ImageView)itemView.findViewById(R.id.image);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,itemName.getText(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}

