package com.example.hamza.karton;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListActivity extends Fragment {
 ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list, container, false);
        pDialog = new ProgressDialog(getActivity().getApplication());
        pDialog.setMessage("Please wait ...");

        return v;
    }
}

