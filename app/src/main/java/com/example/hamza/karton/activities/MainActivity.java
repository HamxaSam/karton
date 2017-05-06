package com.example.hamza.karton.activities;


import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hamza.karton.Fragments.AskFragment;
import com.example.hamza.karton.Fragments.CatagoryFragment;
import com.example.hamza.karton.Fragments.FavouritesFragment;
import com.example.hamza.karton.Fragments.ShareFragment;
import com.example.hamza.karton.R;
import com.example.hamza.karton.adapters.AlbumsAdapter;
import com.example.hamza.karton.language.TypefaceUtil;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.LatestFragment;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.ReportBugFragment;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.SearchFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Locale;

/*SearchView.OnQueryTextListener*/
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // declaration here.........
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    InterstitialAd mInterstitialAd;
    FragmentManager fragmentManager;
    AlbumsAdapter adapter;
    ArrayList albumList;

    private GoogleApiClient client;

    // on back press nevigation drawer
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList,null,null);

//       LocaleHelper.setLocale(getApplicationContext(),"ar-TN");
//       Locale.setDefault(Locale.forLanguageTag("ar"));

        /////adddds by google
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        InterstitialAd mInterstitialAd = new InterstitialAd(getApplicationContext());
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
        });

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // language font working
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Shoroq-Font.ttf");
        toolbar.setTitleMarginStart(290);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorfav));

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.requestLayout();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.setScrimColor(R.color.textColorfav);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            Fragment fragment = new CatagoryFragment();
            navigationView.getMenu().getItem(0).setChecked(true);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
/* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_drawer, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(false);
        searchView.setOnQueryTextListener(this);
      return true;
          }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filter(s);
        return false;
    }*/

    //on navigational item click
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        //item.setChecked(true);
        switch (id) {
            case R.id.Catagory:
                fragment = new CatagoryFragment();
                break;
            case R.id.Search:
                fragment = new SearchFragment();
                Toast.makeText(getApplication(), "search has selected..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Favorite:
                fragment = new FavouritesFragment();
                Toast.makeText(getApplication(), "Favourite has selected..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Latest:
                fragment = new LatestFragment();
                Toast.makeText(getApplicationContext(), "latest episodes are", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Ask:
                fragment = new AskFragment();
                Toast.makeText(getApplicationContext(), "latest episodes are", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Report:
                fragment = new ReportBugFragment();
                Toast.makeText(getApplicationContext(), "Report the Broken episode ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                fragment = new ShareFragment();
                Toast.makeText(getApplicationContext(), "share this App ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Rate:
                try {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="
                                    + getPackageName())));
                }
                break;
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();
        navigationView.setCheckedItem(id);
        drawer.bringToFront();
        drawer.requestLayout();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                // startActivity(new Intent(getApplicationContext(), SearchFragment.class));
                SearchFragment searchFragment = new SearchFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.flContent, searchFragment)
                        .commit();
                Toast.makeText(getApplicationContext(), "search clicked", Toast.LENGTH_SHORT).show();


                return true;
            case R.id.favourite:
                FavouritesFragment favouritesFragment = new FavouritesFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.flContent, favouritesFragment)
                        .commit();
                Toast.makeText(MainActivity.this, "FavouritesFragment clicked", Toast.LENGTH_SHORT).show();
//                a fragment first needs a containetr to get fit in! wo ap na bnaya nei hona khe pa!
//                ak kaam hoskta using dialogebox ap show krwa lain data us ma fragment lg jay ge ?
//                ma bna deta ak sec phr khud adjust kr lain apka app structure huff! Allah! bs theek hogeya assan ha ak se

        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(MainActivity.this, SearchFragment.class);

        }
    }

}

