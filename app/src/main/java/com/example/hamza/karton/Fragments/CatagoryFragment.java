package com.example.hamza.karton.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hamza.karton.Model.Album;
import com.example.hamza.karton.R;
import com.example.hamza.karton.RecyclerView.AlbumsAdapter;
import com.example.hamza.karton.helper.AppConfig;
import com.example.hamza.karton.helper.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import io.vov.vitamio.utils.StringUtils;

public class CatagoryFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList albumList;
    ArrayList<String> arrr;
    ProgressDialog pDialog;
    StringRequest strReq;
    AlbumsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.content_main,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AlbumsAdapter(getActivity(), albumList);
        recyclerView.setAdapter(adapter);
        arrr = new ArrayList<>();
        String str = StringUtils.join(arrr, ",");

        pDialog = new ProgressDialog(getContext());
         pDialog.setMessage("please wait!!");
        if (!pDialog.isShowing())
            pDialog.show();
        final String query = "PLWz5rJ2EKKc_Tt7q77qwyKRgytF1RzRx8";


        try {
           strReq = new StringRequest(Request.Method.GET,
                    AppConfig.URL_YOUTUBE + "&q=" + URLEncoder.encode(query, "UTF-8"), new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    //Log.d(TAG, "Login Response: " + response.toString());
                    if (pDialog.isShowing())
                        pDialog.hide();

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

                            Album album = new Album(title, urll);
                            albumList.add(album);

                        }

                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //                Log.e(TAG, "Login Error: " + error.getMessage());
                    Toast.makeText(getContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    if (pDialog.isShowing())
                        pDialog.hide();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "");
        return view;
    }
    }

