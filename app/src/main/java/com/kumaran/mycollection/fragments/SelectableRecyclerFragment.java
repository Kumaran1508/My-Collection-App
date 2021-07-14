package com.kumaran.mycollection.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kumaran.mycollection.R;
import com.kumaran.mycollection.adapters.SelectableAdapter;
import com.kumaran.mycollection.models.Names;

import java.util.ArrayList;

public class SelectableRecyclerFragment extends Fragment {

    RecyclerView selectables;
    private ArrayList<Names> names;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.selectable_reycler_fragment,container,false);

        selectables = root.findViewById(R.id.selectables);

        names =  new ArrayList<>();
        for (int i=1;i<=25;i++){
            names.add(new Names("Name "+i,false));
        }

        SelectableAdapter adapter = new SelectableAdapter(getContext(),names,selectables);
//        adapter.setHasStableIds(true);

        selectables.setLayoutManager(new LinearLayoutManager(getContext()));
        selectables.setHasFixedSize(true);
        selectables.setAdapter(adapter);

        return root;
    }
}
