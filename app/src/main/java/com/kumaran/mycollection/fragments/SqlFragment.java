package com.kumaran.mycollection.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kumaran.mycollection.R;
import com.kumaran.mycollection.SampleDBSQLiteHelper;

import org.w3c.dom.Text;

import java.io.File;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


public class SqlFragment extends Fragment {

    private EditText name;
    private Button add,display;
    private SQLiteDatabase DB;


    public SqlFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sql, container, false);

        name = view.findViewById(R.id.name);
        add = view.findViewById(R.id.add);
        display = view.findViewById(R.id.display);




        DB=new SampleDBSQLiteHelper(getContext()).getWritableDatabase();
        DB.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR);");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().length()==0){
                    showmessage("error","Enter valid Input");
                }
                else{
                    DB.execSQL("INSERT INTO Student VALUES('"+name.getText()+"');");
                    showmessage("Sucess","Name Added");
                }
                name.clearFocus();
                name.setText("");
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=DB.rawQuery("SELECT * FROM student; ",null );
                if(c.getCount()==0){
                    showmessage("...","No record Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Name: "+c.getString(0)+"\n");
                }
                showmessage("Student Details", buffer.toString());
            }
        });


        return view;
    }

    private void showmessage(String string, String string2) {
// TODO Auto-generated method stub
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(string);
        builder.setMessage(string2);
        builder.show();

    }
}
