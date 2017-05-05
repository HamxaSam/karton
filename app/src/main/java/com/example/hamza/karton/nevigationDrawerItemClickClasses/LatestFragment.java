package com.example.hamza.karton.nevigationDrawerItemClickClasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamza.karton.Model.latest;
import com.example.hamza.karton.R;

import java.util.ArrayList;

public class LatestFragment extends Fragment {
    RecyclerView recyclerView_latest;
    LatestEpisodeAdapter adapter;
    private ArrayList<latest> LatestEpisodeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_latest_fragment, container, false);
        recyclerView_latest = (RecyclerView) v.findViewById(R.id.recyclerView_latest);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView_latest.setLayoutManager(mLayoutManager);
        recyclerView_latest.setItemAnimator(new DefaultItemAnimator());

        LatestEpisodeList = new ArrayList<>();
        latest Ls = new latest("Tom and Jerry", R.drawable.tom);
        LatestEpisodeList.add(Ls);
        Ls = new latest("Tom and Jerry", R.drawable.tom);
        LatestEpisodeList.add(Ls);
        Ls = new latest("Tom and Jerry", R.drawable.tom);
        LatestEpisodeList.add(Ls);
        Ls = new latest("Tom and Jerry", R.drawable.tom);
        LatestEpisodeList.add(Ls);
        Ls = new latest("Tom and Jerry", R.drawable.tom);
        LatestEpisodeList.add(Ls);
        Ls = new latest("Tom and Jerry", R.drawable.tom);
        LatestEpisodeList.add(Ls);

        adapter = new LatestEpisodeAdapter(getActivity(), LatestEpisodeList);
        recyclerView_latest.setAdapter(adapter);

        return v;
    }
}
