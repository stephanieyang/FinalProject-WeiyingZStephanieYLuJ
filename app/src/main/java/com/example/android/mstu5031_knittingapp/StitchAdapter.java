package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class StitchAdapter extends RecyclerView.Adapter<StitchViewHolder> {
    private List<Stitch> stitches;
    private Context context;

    public StitchAdapter(List<Stitch>stitches, Context context){
        this.stitches=stitches;
        this.context=context;

    }
    @Override
    public StitchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stitch_card_view,parent,false);
        return new StitchViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(StitchViewHolder holder, int position) {
        Stitch stitch=stitches.get(position);
        holder.stitchName.setText(stitch.name);
        holder.stitchPhoto.setImageResource(stitch.imageId);

    }

    @Override
    public int getItemCount(){
        return stitches.size();
    }

}


