package com.kumaran.mycollection.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kumaran.mycollection.R;
import com.kumaran.mycollection.adapters.CapitalsAdapter;

import java.util.ArrayList;

public class CapitalsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CapitalsAdapter myAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reference = db.collection("Capitals");
    private ArrayList<CapitalsAdapter.ListElement> list = new ArrayList<>();
    private CapitalsAdapter.ListElement item;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_capitals, container, false);

        spinner = root.findViewById(R.id.spinner);
        String[] sortby = {"Country","Capital","Time"};
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item,sortby);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);




        recyclerView = root.findViewById(R.id.list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        //recyclerView.setHasFixedSize(true);

        myAdapter = new CapitalsAdapter(list, getContext());
        recyclerView.setAdapter(myAdapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reference.orderBy(sortby[i]).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        list.clear();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                            String country = documentSnapshot.get("Country").toString();
                            String capital = documentSnapshot.get("Capital").toString();
                            item = new CapitalsAdapter.ListElement(country,capital);
                            list.add(item);
                        }
                        myAdapter.setLastposition(-1);
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
    }
}
