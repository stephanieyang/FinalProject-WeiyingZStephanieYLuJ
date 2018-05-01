package com.example.android.mstu5031_knittingapp;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by weiyingzhu on 18/4/2.
 */

public class LibraryAdapter extends RecyclerView.Adapter<LibraryViewHolder> {

    private List<UserCreatedPair> lib;
    private Context context;
    LibraryItemClickListener clickListener;

    public LibraryAdapter(List<UserCreatedPair> pattern, Context context) {
        this.lib = pattern;
        this.context = context;

    }

    @Override
    public LibraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_library, parent, false);
        final LibraryViewHolder viewHolder = new LibraryViewHolder(view, this.context);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.onItemClick(v, viewHolder.getPosition());
//            }
//        });
        return new LibraryViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(LibraryViewHolder holder, int position) {
        UserCreatedPair currentLib = lib.get(position);

        holder.patternName.setText(currentLib.getName());
        holder.patternPhoto.setImageResource(Stitch.getDrawableIdFromImgName(currentLib.getStitch()));
        holder.itemPhoto.setImageResource(Item.getDrawableIdFromImgName(currentLib.getItem()));
        holder.idText.setText(currentLib.getId());

    }


    public void onBindViewHolder(LibraryViewHolder holder, int position, int position2) {
        UserCreatedPair currentLib = lib.get(position);

        holder.patternName.setText(currentLib.getName());
        holder.patternPhoto.setImageResource(Stitch.getDrawableIdFromImgName(currentLib.getStitch()));
        holder.itemPhoto.setImageResource(Item.getDrawableIdFromImgName(currentLib.getItem()));
    }

    @Override
    public int getItemCount() {
        return lib.size();
    }
}
