package com.example.hamza.karton.nevigationDrawerItemClickClasses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hamza.karton.R;

public class SearchFragment extends Fragment {
    EditText editText_search;
    Button button_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_search_fragment, container, false);
        editText_search = (EditText) v.findViewById(R.id.editText_search);
        button_search = (Button) v.findViewById(R.id.button_search);

        return v;
    }
}
