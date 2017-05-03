package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.Album;
import com.example.hamza.karton.R;

import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Activity mContext;
    private List<Album> albumList;
    LayoutInflater inflator;

    public AlbumsAdapter(Activity mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
        inflator = mContext.getLayoutInflater();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);

        holder.title.setText(album.getName());
       // holder.thumbnail.setImageResource(R.drawable.pic);

        // loading album cover using Glide library

      Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        }
    }




    @Override
    public int getItemCount() {

        return albumList.size();
    }
}
