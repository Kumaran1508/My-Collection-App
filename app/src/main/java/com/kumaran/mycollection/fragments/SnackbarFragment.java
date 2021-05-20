package com.kumaran.mycollection.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.kumaran.mycollection.R;

public class SnackbarFragment extends Fragment {

    Button getsnack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_snackbar,container,false);

        getsnack = root.findViewById(R.id.show_snackbar);

        getsnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(getsnack, "Button is clicked", Snackbar.LENGTH_LONG);
                View customview = getLayoutInflater().inflate(R.layout.custom_snackbar,null);
                snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                snackbarLayout.setPadding(0,0,0,0);

                Button close = customview.findViewById(R.id.close_btn);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });

                snackbarLayout.addView(customview);
                snackbar.show();


            }});
        return root;
    }
}
