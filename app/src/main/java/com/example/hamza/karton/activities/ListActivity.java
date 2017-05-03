package com.example.hamza.karton.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hamza.karton.R;

public class ListActivity extends AppCompatActivity {
        ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /*pDialog = new ProgressDialog(getActivity().getApplication());
        pDialog.setMessage("Please wait ...");*/
        Intent intent=new Intent();
        String list=intent.getStringExtra("list");
        Toast.makeText(getApplicationContext(),"clicked "+list,Toast.LENGTH_SHORT).show();


    }
}

