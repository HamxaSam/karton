package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.Albumtwo;
import com.example.hamza.karton.R;

import java.util.List;


public class AlbumsAdaptertwo extends RecyclerView.Adapter<AlbumsAdaptertwo.MyViewHolder> {

    private Activity mContext;
    private List<Albumtwo> albumListtwo;
    LayoutInflater inflator;

    public AlbumsAdaptertwo(Activity mContext, List<Albumtwo> albumListtwo) {
        this.mContext = mContext;
        this.albumListtwo = albumListtwo;
        inflator = mContext.getLayoutInflater();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.activity_description_design, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Albumtwo album = albumListtwo.get(position);

        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs());

        // loading album cover using Glide library

        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
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

    @Override
    public int getItemCount() {

        return albumListtwo.size();
    }
}
