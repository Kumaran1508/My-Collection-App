package com.kumaran.mycollection.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kumaran.mycollection.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Instagrid extends Fragment {

    private RecyclerView instagramGrid;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private long images_count;
    private ArrayList<String> urls = new ArrayList<>();
    private InstagridAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.insta_grid_fragment,container,false);

        instagramGrid = page.findViewById(R.id.insta_grid_recycler);

        instagramGrid.setLayoutManager(new GridLayoutManager(getContext(),3));
        instagramGrid.setHasFixedSize(true);

        firestore.collection("insta_grid").document("grid").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                images_count = documentSnapshot.getLong("size");
                getImageUrls();
            }
        });

        adapter = new InstagridAdapter(getContext(),urls);
        instagramGrid.setAdapter(adapter);


        return page;
    }

    private void getImageUrls() {

        for (int positon=0;positon<images_count;positon++){
            addurl(positon);
        }


    }

    private void addurl(int positon) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storage.getReference("grid/"+String.format("%0" + 3 + "d", positon)+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                urls.add(url);
                Collections.sort(urls,Collections.reverseOrder());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
