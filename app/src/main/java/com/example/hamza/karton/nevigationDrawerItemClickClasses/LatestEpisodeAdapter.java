package com.example.hamza.karton.nevigationDrawerItemClickClasses;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.latest;
import com.example.hamza.karton.R;

import java.util.List;

/**
 * Created by Hamza on 5/6/2017.
 */
public class LatestEpisodeAdapter extends RecyclerView.Adapter<LatestEpisodeAdapter.MyViewHolder> {
    LayoutInflater inflator;
    private Activity mContext;
    private List<latest> LatestEpisodeList;

    public LatestEpisodeAdapter(Activity mContext, List<latest> latestEpisodeList) {
        this.mContext = mContext;
        LatestEpisodeList = latestEpisodeList;
        inflator = mContext.getLayoutInflater();
    }

    @Override
    public LatestEpisodeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.latest_fragment_second, parent, false);
        return new LatestEpisodeAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LatestEpisodeAdapter.MyViewHolder holder, int position) {
        latest LE = LatestEpisodeList.get(position);
        holder.title.setText(LE.getNameLatest());
        Glide.with(mContext).load(LE.getImageLatest()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return LatestEpisodeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Latest_Tilte);
            thumbnail = (ImageView) itemView.findViewById(R.id.Latest_thumbnail);
        }
    }
}

