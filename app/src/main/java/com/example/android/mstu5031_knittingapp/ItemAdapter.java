package com.example.android.mstu5031_knittingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(List<Item>items, Context context){
        this.items=items;
        this.context=context;

    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view,parent,false);
        return new ItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item=items.get(position);
        holder.itemName.setText(item.name);
        holder.itemPhoto.setImageResource(item.imageId);

    }

    @Override
    public int getItemCount(){
        return items.size();
    }

}
