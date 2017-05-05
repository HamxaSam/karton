package com.example.hamza.karton.adapters;

import android.app.Activity;
import android.content.Context;
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

    LayoutInflater inflator;

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
       // album_card =(RelativeLayout) v.findViewById(R.id.album_card);
        return new MyViewHolder(v);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public final Context mContext;

        public MyViewHolder(View v) {

            super(v);
            mContext = v.getContext();
            //context = itemView.getContext();
            title = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        }
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);

        holder.title.setText(album.getName());

      /*  album_card.setOnClickListener(new View.OnClickListener() {
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
        });*/
      Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //holder.mContext.startActivity(
               /* Intent sendIntent = new Intent(mContext, ShowListsOne.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "" + CatagoryFragment. + "\" on YouTube");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + video.getId());
                sendIntent.setType("text/plain");
                holder.mContext.startActivity(sendIntent);*/
            }
        });
    }

    @Override
    public int getItemCount() {

        return albumList.size();
    }


}
