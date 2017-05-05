package com.example.hamza.karton.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hamza.karton.Model.Albumone;
import com.example.hamza.karton.R;
import com.example.hamza.karton.adapters.AlbumsAdapterone;
import com.example.hamza.karton.helper.AppConfig;
import com.example.hamza.karton.helper.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ShowListsOne extends AppCompatActivity {

    private RecyclerView recyclerViewone;
    private AlbumsAdapterone adapter;
    private List<Albumone> albumList;
    StringRequest strReq;
    ProgressDialog ppDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists_one);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//        initCollapsingToolbar();
//PLWz5rJ2EKKc_Tt7q77qwyKRgytF1RzRx8
        recyclerViewone = (RecyclerView) findViewById(R.id.recycler_viewone);

        albumList = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerViewone.setLayoutManager(mLayoutManager);
        recyclerViewone.setItemAnimator(new DefaultItemAnimator());
        recyclerViewone.setAdapter(adapter);


        ppDialog = new ProgressDialog(getApplicationContext());
        ppDialog.setMessage("please wait!!");
       /* if (!ppDialog.isShowing())
            ppDialog.show();*/
        final String query = "PLWz5rJ2EKKc_Tt7q77qwyKRgytF1RzRx8";


        try {
            strReq = new StringRequest(Request.Method.GET,
                    AppConfig.URL_YOUTUBE_ITEMS + "&playlistId=" + URLEncoder.encode(query, "UTF-8"), new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    //Log.d(TAG, "Login Response: " + response.toString());
                    if (ppDialog.isShowing())
                        ppDialog.hide();
//&id=PLWz5rJ2EKKc_Tt7q77qwyKRgytF1RzRx8
                    Log.d("MainActivity response", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray itemsJsonArray = jsonObject.getJSONArray("items");
                        for (int i = 0; i < itemsJsonArray.length(); i++) {
                            JSONObject singleVideoJsonOnject = itemsJsonArray.getJSONObject(i);
                            JSONObject snippetObject = singleVideoJsonOnject.getJSONObject("snippet");
                            String title = snippetObject.getString("title");

                            JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");

                            JSONObject defaultObject = thumbnailObject.getJSONObject("default");
                            String urll = defaultObject.getString("url");
                            String views="20k";
                            Albumone albumone = new Albumone(title,views, urll);
                            albumList.add(albumone);


                        }
                        adapter = new AlbumsAdapterone(ShowListsOne.this, albumList);

                        recyclerViewone.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //                Log.e(TAG, "Login Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    if (ppDialog.isShowing())
                        ppDialog.hide();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "");

    }
         /* try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/



    }

    /**
     * Adding few albums for testing
     *//*
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};




        Album a = new Album("True Romance", 13, covers[0]);
        albumList.add(a);

        a = new Album();
        a.setName("ABC");
        a.setNumOfSongs(3);
        a.setThumbnail(R.drawable.album1);
        albumList.add(a);

        a = new Album("Xscpae", 8, covers[1]);
        albumList.add(a);

        a = new Album("Maroon 5", 11, covers[2]);
        albumList.add(a);

        a = new Album("Born to Die", 12, covers[3]);
        albumList.add(a);

        a = new Album("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        albumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);


    }*/
