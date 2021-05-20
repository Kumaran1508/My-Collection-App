package com.kumaran.mycollection.fragments;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kumaran.mycollection.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;

public class HomeFragment extends Fragment {
    private TextView homeText;
    private Button click;
    private EditText fileName, data;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        click = root.findViewById(R.id.click);
        homeText = root.findViewById(R.id.home_text);
        fileName = root.findViewById(R.id.file_name);
        data = root.findViewById(R.id.data);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setText("button clicked");


                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // Do the file write
                    writefile();
                } else {
                    // Request permission from the user
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }



            }
        });

        return root;
    }

    private void writefile(){
        FileOutputStream fos = null;

        File root = new File(Environment.getExternalStorageDirectory(), "My Collection");
        if (!root.exists()) {
            Toast.makeText(getContext(), ""+root.mkdir(), Toast.LENGTH_SHORT).show();
        }
        File filepath = new File(root, fileName.getText().toString()+".txt");

        try {
            fos = new FileOutputStream(filepath);
            fos.write(data.getText().toString().getBytes());
            data.getText().clear();
            Toast.makeText(getContext(), "Saved to " + Environment.getExternalStorageDirectory().getPath() + "/My Collection/" + fileName.getText().toString(),
                    Toast.LENGTH_LONG).show();
            fileName.getText().clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                // Re-attempt file write
                writefile();break;
        }
    }
}
