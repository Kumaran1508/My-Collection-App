package com.kumaran.mycollection.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.kumaran.mycollection.R;

public class DownloadsFragment extends Fragment {
    private TextView downloadsText;
    private Button dclick;
    private EditText mobileNumber,messageContent;

    public DownloadsFragment() {
        super(R.layout.fragment_downloads);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_downloads,container,false);
        downloadsText = root.findViewById(R.id.downloads_text);
        dclick = root.findViewById(R.id.d_click);
        mobileNumber = root.findViewById(R.id.number);
        messageContent = root.findViewById(R.id.message_content);

        dclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    // Send the sms
                    sendMessage();
                } else {
                    // Request permission from the user
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.SEND_SMS}, 0);
                }


            }
        });


        return root;
    }

    private void sendMessage(){
        String phno = mobileNumber.getText().toString();
        String sms = messageContent.getText().toString();
        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phno,null,sms,null,null);
            Toast.makeText(getContext(), "SMS Sent!",Toast.LENGTH_LONG).show();
            mobileNumber.setText("");
            messageContent.setText("");
        }
        catch(Exception e){
            Toast.makeText(getContext(),
                    "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:
                sendMessage();break;
        }
    }
}
