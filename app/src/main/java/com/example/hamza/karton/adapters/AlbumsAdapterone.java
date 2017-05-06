package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.Albumone;
import com.example.hamza.karton.R;
import com.example.hamza.karton.activities.DescriptionActivity;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapterone extends RecyclerView.Adapter<AlbumsAdapterone.MyViewHolder> {

    private Activity mContext;
    private List<Albumone> albumListone;
    LayoutInflater inflator;
    RelativeLayout layout_episode;

    public AlbumsAdapterone(Activity mContext, List<Albumone> albumList) {
        this.mContext = mContext;
        this.albumListone = albumList;
        inflator = mContext.getLayoutInflater();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.listview_episode, parent, false);
        layout_episode=(RelativeLayout)v.findViewById(R.id.layout_episode);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Albumone album = albumListone.get(position);

        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs());
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        layout_episode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                for(int i=0;i<=position;i++){
                    intent=new Intent(mContext, DescriptionActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            count = (TextView) v.findViewById(R.id.count);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);


        }
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */





    @Override
    public int getItemCount() {

        return albumListone.size();
    }
}
