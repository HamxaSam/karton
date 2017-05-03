package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamza.karton.Model.EpisodeList;
import com.example.hamza.karton.R;

import java.util.ArrayList;

/**
 * Created by Hamza on 5/2/2017.
 */
public class CustomEpisodeAdapter extends ArrayAdapter<EpisodeList> {
    Activity activity;
    ArrayList<EpisodeList> ArrayListEpisode;
    LayoutInflater inflater;

    public CustomEpisodeAdapter(FragmentActivity activity, ArrayList<EpisodeList> ArrayListEpisode) {
        super(activity, R.layout.listview_episode, ArrayListEpisode);
        this.activity = activity;
        this.ArrayListEpisode = ArrayListEpisode;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        // view set = layout
        if (v == null) {
            v = inflater.inflate(R.layout.listview_episode, null);
            ImageView image = (ImageView) v.findViewById(R.id.imageView_listEpisode);
            TextView title = (TextView) v.findViewById(R.id.textView_listEpisodeTitle);
            TextView views = (TextView) v.findViewById(R.id.textView_views);

            EpisodeList episode = ArrayListEpisode.get(position);

            image.setImageResource(episode.getImage());
            title.setText(episode.getTitle());
            views.setText(episode.getViews());
        }
        return v;

    }
}