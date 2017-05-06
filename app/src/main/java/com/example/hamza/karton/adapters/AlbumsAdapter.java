package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hamza.karton.Model.Album;
import com.example.hamza.karton.R;
import com.example.hamza.karton.activities.ShowListsOne;
import com.github.siyamed.shapeimageview.RoundedImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {
    LayoutInflater inflator;
    RelativeLayout layout_album;
    Context context;
    private Activity mContext;
    private List<Album> albumList;
    private List<Album> albumListFilter;
    String murl;
    String mPlayList;  // the da
 //   ArrayList<ThumbContainer> List;
    Intent intent;
    public AlbumsAdapter(Activity mContext, List<Album> albumList, String mPlayList, String murl) {
        this.mContext = mContext;
        this.albumList = albumList;
        inflator = mContext.getLayoutInflater();
        this.murl = murl;
        this.mPlayList = mPlayList;
    }
    /* public AlbumsAdapter(Context context, ArrayList<ThumbContainer> List) {
        this.mContext = mContext;
        this.List=List;
    }
*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflator.inflate(R.layout.album_card, parent, false);
        layout_album = (RelativeLayout) v.findViewById(R.id.layout_album);
       // album_card =(RelativeLayout) v.findViewById(R.id.album_card);
        return new MyViewHolder(v);
    }
    /*   public void filter(String s) {
      albumList.clear();
        if (s.length() == 0) {
            albumList.addAll(albumListFilter);
        }
        else
        {
            for (Album album : albumListFilter)
            {
                if (album.getName().toLowerCase(Locale.getDefault()).contains( s.toLowerCase(Locale.getDefault())))
                {
                    albumList.add(album);
                }
            }
        }
        notifyDataSetChanged();
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RoundedImageView thumbnail;
        public final Context mContext;

        public MyViewHolder(View v) {

            super(v);
            mContext = v.getContext();
            //context = itemView.getContext();
            title = (TextView) v.findViewById(R.id.title);
            thumbnail = (RoundedImageView) v.findViewById(R.id.thumbnail);
        }
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);

        holder.title.setText(album.getName());
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
      //  ArrayList<String> arrayList = getIntent().getExtras().getStringArrayList("List");
        layout_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0;i<=position;i++){
                    intent =  new Intent(mContext, ShowListsOne.class);
                    intent.putExtra("desiredUrl",murl);
                    intent.putExtra("albumList",mPlayList);
                   /* String playlist=intent.getStringExtra("playlist");
                    String thumb=intent.getStringExtra("thumb");
                    Toast.makeText(mContext,"thubm url"+thumb+playlist,Toast.LENGTH_LONG).show();*/
                    mContext.startActivity(intent);
                }
               /*
                switch (position){
                    case 0:
                        Toast.makeText(mContext,"clicked",Toast.LENGTH_SHORT).show();

                    default:

                }*/
            }
        });
        /*  holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //holder.mContext.startActivity(
               *//* Intent sendIntent = new Intent(mContext, ShowListsOne.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "" + CatagoryFragment. + "\" on YouTube");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + video.getId());
                sendIntent.setType("text/plain");
                holder.mContext.startActivity(sendIntent);*//*
            }
        });*/
    }
    @Override
    public int getItemCount() {

        return albumList.size();
    }


}
