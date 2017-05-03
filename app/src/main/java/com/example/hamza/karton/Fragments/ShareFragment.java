package com.example.hamza.karton.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hamza.karton.R;

public class ShareFragment extends Fragment {
    Button share;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_share_fragment, container, false);
        share = (Button) v.findViewById(R.id.button_share);

        String message = "Text I want to share.";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
        return v;

    }
}

