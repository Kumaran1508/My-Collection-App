package com.kumaran.mycollection.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kumaran.mycollection.R;

import java.util.ArrayList;

public class InstagridAdapter extends RecyclerView.Adapter<InstagridAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> urls;

    public InstagridAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View grid_item = inflater.inflate(R.layout.grid_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(grid_item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(urls.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.grid_image);
        }
    }
}
