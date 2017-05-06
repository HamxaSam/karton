package com.example.hamza.karton.nevigationDrawerItemClickClasses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamza.karton.Model.User;
import com.example.hamza.karton.R;
import com.example.hamza.karton.helper.SQLiteHandler;

import java.util.Locale;

public class SearchFragment extends Fragment {
    EditText editText_search;
    Button button_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_search_fragment, container, false);
        editText_search = (EditText) v.findViewById(R.id.editText_search);
        final String playList=editText_search.getText().toString();
        button_search = (Button) v.findViewById(R.id.button_search);
        final SQLiteHandler sqLiteHandler=new SQLiteHandler(getContext());
        final User user=new User();
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setEmail(playList);
               Boolean addUser=sqLiteHandler.addUser(user);
                if (addUser){
                    Toast.makeText(getContext(),"playList added",Toast.LENGTH_SHORT).toString();
                }
                else {
                    Toast.makeText(getContext(),"playList already added",Toast.LENGTH_SHORT).toString();
                }
            }
        });
        return v;
    }
}
