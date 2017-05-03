package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.favEpisode;
import com.example.hamza.karton.R;

import java.util.List;

/**
 * Created by Hamza on 5/3/2017.
 */
public class FavEpisodeAdapter extends RecyclerView.Adapter<FavEpisodeAdapter.MyViewHolder> {
    private Activity mContext;
    private List<favEpisode> favEpisodeList;
    LayoutInflater inflator;

    public FavEpisodeAdapter(Activity mContext, List<favEpisode> favEpisodeList) {
        this.mContext = mContext;
        this.favEpisodeList = favEpisodeList;
        inflator = mContext.getLayoutInflater();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.favouritepisode, parent, false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        favEpisode fe = favEpisodeList.get(position);
        holder.title.setText(fe.getTitle());
        Glide.with(mContext).load(fe.getImage()).into(holder.thumbnail);

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.fav_title);
            thumbnail = (ImageView) itemView.findViewById(R.id.fav_thumbnail);
        }
    }
    @Override
    public int getItemCount() {
         return favEpisodeList.size();
    }
}

