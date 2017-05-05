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
import com.example.hamza.karton.Model.Albumtwo;
import com.example.hamza.karton.R;
import com.example.hamza.karton.adapters.AlbumsAdaptertwo;
import com.example.hamza.karton.helper.AppConfig;
import com.example.hamza.karton.helper.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

    private RecyclerView recyclerViewtwo;
    private AlbumsAdaptertwo adapter;
    private List<Albumtwo> albumList;
    StringRequest strReq;
    ProgressDialog ppDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//        initCollapsingToolbar();
//PLWz5rJ2EKKc_Tt7q77qwyKRgytF1RzRx8
        recyclerViewtwo = (RecyclerView) findViewById(R.id.recycler_viewtwo);

        albumList = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerViewtwo.setLayoutManager(mLayoutManager);
        recyclerViewtwo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewtwo.setAdapter(adapter);


        ppDialog = new ProgressDialog(getApplicationContext());
        ppDialog.setMessage("please wait!!");
       /* if (!ppDialog.isShowing())
            ppDialog.show();*/
        final String query = "xdItHEVfQ4U";


        try {
            strReq = new StringRequest(Request.Method.GET,
                    AppConfig.URL_YOUTUBE_VIDEOS + "&id=" + URLEncoder.encode(query, "UTF-8"), new Response.Listener<String>() {

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
                            String description=snippetObject.getString("description");
                            JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");

                            JSONObject defaultObject = thumbnailObject.getJSONObject("default");
                            String urll = defaultObject.getString("url");

                            Albumtwo albumtwo = new Albumtwo(title,description, urll);
                            albumList.add(albumtwo);


                        }
                        adapter = new AlbumsAdaptertwo(DescriptionActivity.this, albumList);

                        recyclerViewtwo.setAdapter(adapter);
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
}
