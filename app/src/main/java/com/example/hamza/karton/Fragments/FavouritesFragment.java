package com.example.hamza.karton.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamza.karton.Model.favEpisode;
import com.example.hamza.karton.R;
import com.example.hamza.karton.adapters.FavEpisodeAdapter;

import java.util.ArrayList;


public class FavouritesFragment extends Fragment {
    RecyclerView recyclerView;
    FavEpisodeAdapter adapter;
    private ArrayList<favEpisode> fvEpisodeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_favourite, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_fav);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fvEpisodeList = new ArrayList<>();

        favEpisode Fv = new favEpisode("Tom and Jerry", R.drawable.tom);
        fvEpisodeList.add(Fv);
        Fv = new favEpisode("Tom and Jerry", R.drawable.tom);
        fvEpisodeList.add(Fv);
        Fv = new favEpisode("Tom and Jerry", R.drawable.tom);
        fvEpisodeList.add(Fv);
        Fv = new favEpisode("Tom and Jerry", R.drawable.tom);
        fvEpisodeList.add(Fv);
        Fv = new favEpisode("Tom and Jerry", R.drawable.tom);
        fvEpisodeList.add(Fv);
        Fv = new favEpisode("Tom and Jerry", R.drawable.tom);
        fvEpisodeList.add(Fv);


        adapter = new FavEpisodeAdapter(getActivity(), fvEpisodeList);
        recyclerView.setAdapter(adapter);

        return v;

    }
}
