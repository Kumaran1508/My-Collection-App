package com.kumaran.mycollection.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kumaran.mycollection.R;

import java.util.Timer;
import java.util.TimerTask;


public class MultiThreading extends Fragment {

    TextView text1,text2;
    Timer timer = new Timer();
    int t1=0;
    private int t2=100;
    private TimerTask timerTask;
    Handler hand = new Handler();
    Handler hand2 = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            set();
        }
    };
    Runnable run2 = new Runnable() {
        @Override
        public void run() {
            set2();
        }
    };

    private void set2() {
        t2-=1;
        text2.setText(t2+"");
        if (t2>0) hand2.postDelayed(run2, 100);
    }

    private void set() {
        t1+=1;
        text1.setText(t1+"");
        if (t1<10) hand.postDelayed(run, 1000);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_multi_threading,container,false);
        text1 = root.findViewById(R.id.text1);
        text2 = root.findViewById(R.id.text2);

        hand.postDelayed(run, 1000);
        hand2.postDelayed(run2,500);



        return root;
    }




}