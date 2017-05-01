package com.example.hamza.karton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hamza.karton.Model.Album;
import com.example.hamza.karton.RecyclerView.AlbumsAdapter;
import com.example.hamza.karton.helper.AppConfig;
import com.example.hamza.karton.helper.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    ProgressDialog pDialog;
    StringRequest strReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();


        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);


        pDialog = new ProgressDialog(MainActivity.this);
        // pDialog.setMessage("please wait!!");
        if (!pDialog.isShowing())
            pDialog.show();
        final String query = "cartoon";


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
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        adapter = new AlbumsAdapter(MainActivity.this, albumList);
                        recyclerView.setAdapter(adapter);
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

    }

