package com.example.hamza.karton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hamza.karton.Model.EpisodeList;

import java.util.ArrayList;

/**
 * Created by Usman on 5/4/2017.
 */

public class ListActivity extends Fragment {
 ProgressDialog pDialog;
    ListView listView;

    /* override method*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list, container, false);
        pDialog = new ProgressDialog(getActivity().getApplication());
        pDialog.setMessage("Please wait ...");
        listView = (ListView) v.findViewById(R.id.listView_detail);
        ArrayList<EpisodeList> ArrayListEpisode = new ArrayList<>();

/*
        EpisodeList El = new EpisodeList("Tom & jerry", "View:200", R.drawable.tom);
        ArrayListEpisode.add(El);

        El = new EpisodeList("Tom & jerry", "View:200", R.drawable.tom);
        ArrayListEpisode.add(El);

        El = new EpisodeList("Tom & jerry", "View:200", R.drawable.tom);
        ArrayListEpisode.add(El);

        CustomEpisodeAdapter adapter = new CustomEpisodeAdapter(getActivity(), ArrayListEpisode);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
*/
        return v;
    }
}
