package com.kumaran.mycollection.fragments;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kumaran.mycollection.R;


public class BottomNavigationFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;



    public BottomNavigationFragment() {
        // Required empty public constructorott
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
        bottomNavigationView = root.findViewById(R.id.bottom_nav);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.btm_nav_container,HomeFragment.class,null)
                .commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                switch (item.getItemId()){
                    case R.id.bottom_nav_home:
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.btm_nav_container,HomeFragment.class,null)
                                .commit();
                        break;

                    case R.id.bottom_nav_downloads:
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.btm_nav_container,DownloadsFragment.class,null)
                                .commit();
                        break;
                }
                return true;
            }
        });





        return root;
    }
}