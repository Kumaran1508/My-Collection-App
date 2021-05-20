package com.kumaran.mycollection.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.kumaran.mycollection.R;



public class NotificationFragment extends Fragment {

    private Button sendNotification;
    private NotificationManager notificationManager;
    private NotificationChannel notificationChannel;
    private NotificationCompat.BigTextStyle bigTextStyle;
    private boolean custom = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.fragment_customnotifications,container,false);

        sendNotification = page.findViewById(R.id.btn_notify);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNotification();
            }
        });

        return page;
    }

    void  setNotification(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("112","Random", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
            bigTextStyle =new NotificationCompat.BigTextStyle();
            bigTextStyle.bigText("This is a very long paragraph. If not , at least a paragraph");


            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "112");
            builder.setSmallIcon(R.drawable.logo);
            builder.setContentTitle("Friday");
            builder.setContentText("Mobile App Development Lab");
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setStyle(bigTextStyle);

            if (custom==false){
                custom = true;
                notificationManager.notify(1, builder.build());
                return;
            }


            RemoteViews notificationcollapsed = new RemoteViews(getActivity().getPackageName(),R.layout.custom_notification);
            RemoteViews notificationexpanded = new RemoteViews(getActivity().getPackageName(),R.layout.customnotificationexpanded);

            builder.setContentTitle("Saturday")
                    .setCustomContentView(notificationcollapsed)
                    .setCustomBigContentView(notificationexpanded);

            if (custom==true){
                custom = false;
                notificationManager.notify(1, builder.build());
                return;
            }



        }

    }

}
