package com.kumaran.mycollection.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kumaran.mycollection.R;
import com.kumaran.mycollection.adapters.MyPagerAdapter;


public class ViewPagerFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view_pager, container, false);

        viewPager = root.findViewById(R.id.viewpager);
        tabLayout = root.findViewById(R.id.tab_layout);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter(pagerAdapter);

        String[] tabNames = {"SnackBar","Chips"};
        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> tab.setText(tabNames[position])).attach();

        return root;
    }
}