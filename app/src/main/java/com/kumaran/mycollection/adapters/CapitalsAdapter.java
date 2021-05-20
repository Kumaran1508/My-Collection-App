package com.kumaran.mycollection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.kumaran.mycollection.R;


import java.util.ArrayList;

public class CapitalsAdapter extends RecyclerView.Adapter<CapitalsAdapter.MyHolder> {

    private ArrayList<CapitalsAdapter.ListElement> list;
    private Context context;
    private int lastposition;


    public CapitalsAdapter(ArrayList<ListElement> list, Context context) {
        this.list = list;
        this.context = context;
        lastposition = -1;
    }

    public void setLastposition(int lastposition) {
        this.lastposition = lastposition;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.country.setText(list.get(position).country);
        holder.capital.setText(list.get(position).capital);

        if (position > lastposition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
            animation.setStartOffset(position*100);
            holder.itemView.startAnimation(animation);
            lastposition = position;
        }


    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHolder extends  RecyclerView.ViewHolder{

        private MaterialTextView capital;
        private MaterialTextView country;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.country);
            capital = itemView.findViewById(R.id.capital);
        }
    }

    public static class ListElement {
        private String country;
        private String capital;

        public ListElement(String country, String capital) {
            this.country = country;
            this.capital = capital;
        }


    }
}
