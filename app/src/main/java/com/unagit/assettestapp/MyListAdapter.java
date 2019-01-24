package com.unagit.assettestapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private final ArrayList<String> assets = new ArrayList<>();
    private ListClickListener listener;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder(@NonNull TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }
    MyListAdapter(String[] assets, ListClickListener listener) {
        List<String> temp = Arrays.asList(assets);
        this.assets.addAll(temp);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String fileName = assets.get(i);
        myViewHolder.textView.setText(fileName);
        myViewHolder.textView.setOnClickListener( v -> {
            listener.onClick(fileName);
        });
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }

    interface ListClickListener {
        void onClick(String fileName);
    }
}
