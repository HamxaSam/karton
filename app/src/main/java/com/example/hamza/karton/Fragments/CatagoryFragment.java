package com.example.hamza.karton.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hamza.karton.Model.Album;
import com.example.hamza.karton.Model.User;
import com.example.hamza.karton.R;
import com.example.hamza.karton.adapters.AlbumsAdapter;
import com.example.hamza.karton.helper.AppConfig;
import com.example.hamza.karton.helper.AppController;
import com.example.hamza.karton.helper.SQLiteHandler;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.SearchFragment;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class CatagoryFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Album> albumList;
    InterstitialAd mInterstitialAd;
    ProgressDialog pDialog;
    StringRequest strReq;
    ArrayList<User> retList;
    AlbumsAdapter adapter;
    Intent intent;
    String urll;
    String playList;



    // ArrayList<ThumbContainer> List;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        intent = new Intent(getContext(), AlbumsAdapter.class);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //   List=new ArrayList();

//        arrr = new ArrayList<>();
//        String str = StringUtils.join(arrr, ",");

/*
        /////adddds by google
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        InterstitialAd mInterstitialAd = new InterstitialAd(getContext());
        // set the ad unit ID

        mInterstitialAd.setAdUnitId(getString(R.string.banner_home_footer));
        adRequest = new AdRequest.Builder()
                .build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });*/


        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("please wait!!");
        if (!pDialog.isShowing())
            pDialog.show();
        final String query = "PLO4o8yV-G3LN4PAkp_-l-jLgyQU2w_6Y9";

      /*  User user=new User();
        user.setEmail(query);
        SQLiteHandler handler=new SQLiteHandler(getContext()); Boolean added=handler.addUser(user);
        if(added){
            Toast.makeText(getContext(),"data added success",Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(getContext(),"data not added",Toast.LENGTH_SHORT).show();
        }*/
        SQLiteHandler handler = new SQLiteHandler(getContext());
        retList = new ArrayList();
        retList = handler.getAllUsers();
        for (int i = 0; i < retList.size(); i++) {
            User user1 = retList.get(i);
            playList = user1.getEmail();

            try {
                ;
                strReq = new StringRequest(Request.Method.GET,
                        AppConfig.URL_YOUTUBE + "&id=" + URLEncoder.encode(playList, "UTF-8"), new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "Login Response: " + response.toString());
                        if (pDialog.isShowing())
                            pDialog.hide();
//&id=PLWz5rJ2EKKc_Tt7q77qwyKRgytF1RzRx8
                        Log.d("MainActivity response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray itemsJsonArray = jsonObject.getJSONArray("items");
                            int  itemArraySize = itemsJsonArray.length();
                            for (int i = 0; i <itemArraySize ; i++) {
                                JSONObject singleVideoJsonOnject = itemsJsonArray.getJSONObject(i);
                                JSONObject snippetObject = singleVideoJsonOnject.getJSONObject("snippet");
                                String title = snippetObject.getString("title");

                                JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");

                                JSONObject defaultObject = thumbnailObject.getJSONObject("default");
                                urll = defaultObject.getString("url");

                                Album album = new Album(title, urll);
                                albumList.add(album);
                                /*intent.putExtra("thumb",urll);
                                intent.putExtra("playlist",playList);*/
                                /*ThumbContainer thumbContain=new ThumbContainer(playList,urll);
                                List.add(thumbContain);*/
                                //recyclerView.setAdapter(new AlbumsAdapter(getContext(),List));
                            }


                            adapter = new AlbumsAdapter(getActivity(), albumList,urll,playList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            //   startActivity(intent);
                          /*  public List getData1() {

                                List<TimelineDataStore> data = new ArrayList<>();

                                int[] icons = {R.mipmap.human_image, R.mipmap.human_image, R.mipmap.human_image, R.mipmap.human_image, R.mipmap.human_image, R.mipmap.human_image, R.mipmap.human_image};
                                Bitmap[] images = profileImageAfterDownload;
                                ArrayList<String> titles = postHeader;

                                for (int i = 0; i < titles.size() && i < icons.length && i < noOfDays.size() && i < postTitle.size(); i++) {

                                    TimelineDataStore current = new TimelineDataStore();
                                    current.images = images[i];
                                    //current.iconId = icons[i];
                                    current.title = titles.get(i);


                                    data.add(current);
                                }
                                return data;
                            }*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        //                Log.e(TAG, "Login Error: " + error.getMessage());
//                        Toast.makeText(getContext(),
//                                error.getMessage(), Toast.LENGTH_LONG).show();
                        if (pDialog.isShowing())
                            pDialog.hide();
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, "");
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.right_drawer, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(getContext(), SearchFragment.class));
                Toast.makeText(getContext(), "search clicked", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity.this,SearchFragment.class);


                return true;
            case R.id.favourite:
                startActivity(new Intent(getContext(), FavouritesFragment.class));
                Toast.makeText(getContext(), "FavouritesFragment clicked", Toast.LENGTH_SHORT).show();


        }
        return true;

    }
}