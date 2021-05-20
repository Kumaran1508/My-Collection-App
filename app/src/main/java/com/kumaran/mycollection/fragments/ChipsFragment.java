package com.kumaran.mycollection.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.kumaran.mycollection.R;

public class ChipsFragment extends Fragment {

    private ChipGroup firstGroup,secondGroup,thirdGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chips,container,false);

        firstGroup = root.findViewById(R.id.first_group);
        secondGroup = root.findViewById(R.id.second_group);
        thirdGroup = root.findViewById(R.id.third_group);

        String[] genres = {"Thriller", "Comedy", "Adventure"};

        for(String genre : genres) {
            Chip chip = new Chip(new ContextThemeWrapper(getContext(), R.style.Theme_MaterialUITests_defChipStyle_Filter));
            chip.setText(genre);
            chip.setCheckable(true);
            firstGroup.addView(chip);

        }
        

        for(String genre : genres) {
            Chip chip = new Chip(new ContextThemeWrapper(getContext(), R.style.Theme_MaterialUITests_defChipStyle_Entry));
            chip.setText(genre);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    return;
                }
            });
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    secondGroup.removeView(v);
                }
            });
            secondGroup.addView(chip);
        }

        for(String genre : genres) {
            Chip chip = new Chip(new ContextThemeWrapper(getContext(), R.style.Theme_MaterialUITests_defChipStyle_Choice));
            chip.setText(genre);
            chip.setCheckable(true);
            thirdGroup.addView(chip);
        }


        return root;
    }
}
