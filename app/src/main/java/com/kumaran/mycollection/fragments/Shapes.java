package com.kumaran.mycollection.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kumaran.mycollection.R;


public class Shapes extends Fragment {



    public Shapes() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setContentView(R.layout.fragment_shapes);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shapes,container,false);
        LinearLayout linearLayout = view.findViewById(R.id.shapes_container);
        linearLayout.addView(new GraphPrim(getContext()));
        return view;
    }

    private class GraphPrim extends View {

        Paint paint = new Paint();

        public GraphPrim(Context applicationContext) {
            super(applicationContext);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(6);
            canvas.drawCircle(100, 100, 50, paint);

            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(250, 250, 50, paint);
            canvas.drawRect(150,400,300,500, paint);

            paint.setColor(Color.RED);
            canvas.drawRect(450,50,400,350, paint);
            
            paint.setColor(Color.YELLOW);
            canvas.drawLine(520, 850, 520, 1150, paint);
            canvas.drawLine(250, 600, 350, 600, paint);
        }
    }
}