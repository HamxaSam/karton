package com.example.hamza.karton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hamza.karton.Model.favEpisode;
import com.example.hamza.karton.adapters.FavEpisodeAdapter;

import java.util.ArrayList;



public class favourite extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog pDialog;
    FavEpisodeAdapter adapter;
    private ArrayList<favEpisode> fvEpisodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_fav);


        fvEpisodeList = new ArrayList<>();
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //  adapter = new FavEpisodeAdapter((favourite)getApplicationContext(), fvEpisodeList);
        recyclerView.setAdapter(adapter);

        if (recyclerView == null)
            pDialog = new ProgressDialog(getApplicationContext());
        pDialog.setMessage("please wait!!");
        if (!pDialog.isShowing())
            pDialog.show();

    }
}
