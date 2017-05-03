package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.Album;
import com.example.hamza.karton.R;
import com.example.hamza.karton.activities.ListActivity;

import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    LayoutInflater inflator;
    RelativeLayout album_card;
    Context context;
    private Activity mContext;
    private List<Album> albumList;
    public AlbumsAdapter(Activity mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
        inflator = mContext.getLayoutInflater();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.album_card, parent, false);
        album_card =(RelativeLayout) v.findViewById(R.id.album_card);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);

        holder.title.setText(album.getName());

        album_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (position){

                    default:
                        intent =  new Intent(context, ListActivity.class);
                        intent.putExtra("list",position);
                        context.startActivity(intent);
                        break;
                }
            }
        });
      Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {

        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            title = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        }
    }
}
