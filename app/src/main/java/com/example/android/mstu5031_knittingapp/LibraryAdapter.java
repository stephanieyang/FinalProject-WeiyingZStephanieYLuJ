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

    private List<KnitLibrary> lib;
    private Context context;

    public LibraryAdapter(List<KnitLibrary> pattern, Context context) {
        this.lib = pattern;
        this.context = context;

    }

    @Override
    public LibraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_library, parent, false);
        return new LibraryViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(LibraryViewHolder holder, int position) {

    }


    public void onBindViewHolder(LibraryViewHolder holder, int position, int position2) {
        KnitLibrary currentLib = lib.get(position);
        holder.patternName.setText(currentLib.name);
        holder.patternPhoto.setImageResource(currentLib.photoId);
        holder.patternPhoto.setImageResource(currentLib.photo2Id);

    }

    @Override
    public int getItemCount() {
        return lib.size();
    }
}
