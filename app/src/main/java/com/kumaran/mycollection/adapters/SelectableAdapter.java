package com.kumaran.mycollection.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kumaran.mycollection.R;
import com.kumaran.mycollection.models.Names;

import java.util.ArrayList;

public class SelectableAdapter extends RecyclerView.Adapter<SelectableAdapter.ViewHolder> {

    private Context context;
    private RecyclerView selectables;
    private ArrayList<Names> list;
    boolean isSelectionMode = false,isSelectAll = false;
    int min_select_position ,max_select_position;
    private ArrayList<Names> selected=new ArrayList<>();


    public SelectableAdapter(Context context, ArrayList<Names> names,RecyclerView selectables) {
        this.context = context;
        this.selectables = selectables;
        list = names;
        min_select_position=list.size()+1;
        max_select_position=-1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.selectable_item,parent,false);
        ViewHolder holder = new ViewHolder(root);

        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());

        if ((list.get(position).isSelected())){
            holder.selectable_check.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else{
            holder.selectable_check.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        if (isSelectAll) {
            holder.selectable_check.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        holder.itemView.setOnClickListener(v -> {
            if (isSelectionMode){
                if (holder.selectable_check.getVisibility() == View.INVISIBLE){
                    holder.selectable_check.setVisibility(View.VISIBLE);
                    holder.itemView.setBackgroundColor(Color.LTGRAY);
                    list.get(position).setSelected(true);
                    selected.add(list.get(position));
                    if (position>max_select_position)
                        max_select_position = position;
                    if (position<min_select_position)
                        min_select_position = position;

                }
                else {
                    holder.selectable_check.setVisibility(View.INVISIBLE);
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    list.get(position).setSelected(false);
                    selected.remove(list.get(position));

                    for(Names name : list){
                        int current_position=list.indexOf(name);
                        if(name.isSelected() && current_position<min_select_position) min_select_position = current_position;
                        if(name.isSelected() && current_position>max_select_position) max_select_position = current_position;
                    }

                }
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (!isSelectionMode) {

                ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        MenuInflater inflater = mode.getMenuInflater();
                        inflater.inflate(R.menu.selection_menu,menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        isSelectionMode = true;
                        holder.itemView.callOnClick();
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete_selected:
                                for (Names name : selected){
                                    list.remove(name);
                                }
                                mode.finish();
                                break;
                            case R.id.select_all:
                                isSelectAll = !isSelectAll;
                                selected.clear();
                                if(isSelectAll) selected.addAll(list);
                                for (Names name:list) name.setSelected(isSelectAll);
                                notifyDataSetChanged();
                                break;
                            case R.id.select_interval:
                                if (!(selected.size()>=2)){
                                    Toast.makeText(context, "selection not possible", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                for (int pos =min_select_position+1;pos<max_select_position;pos++){
                                    try{
                                        RecyclerView.ViewHolder viewHolder = selectables.getChildViewHolder(selectables.getChildAt(pos));
                                        ImageView check = viewHolder.itemView.findViewById(R.id.selectable_check);
                                        if (check.getVisibility() == View.INVISIBLE)
                                            viewHolder.itemView.callOnClick();
                                    }catch (Exception e){
                                        Toast.makeText(context, "selection error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        isSelectionMode = false;
                        isSelectAll = false;
                        selected.clear();
                        min_select_position=list.size()+1;
                        max_select_position=-1;
                        notifyDataSetChanged();
                    }
                };

                ((AppCompatActivity) v.getContext()).startActionMode(callback,ActionMode.TYPE_PRIMARY);
            }

            return true;
        });

    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
//        holder.selectable_check.setVisibility(View.INVISIBLE);
//        holder.itemView.setBackgroundColor(Color.TRANSPARENT);
//        if (list.get(holder.getLayoutPosition()).isSelected()){
//            holder.selectable_check.setVisibility(View.VISIBLE);
//            holder.itemView.setBackgroundColor(Color.LTGRAY);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView selectable_check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.selectable_item_text);
            selectable_check = itemView.findViewById(R.id.selectable_check);
        }
    }
}
