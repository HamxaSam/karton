package com.example.hamza.karton;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hamza.karton.Fragments.CatagoryFragment;
import com.example.hamza.karton.Fragments.ShareFragment;
import com.example.hamza.karton.language.TypefaceUtil;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.LatestFragment;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.ReportBugFragment;
import com.example.hamza.karton.nevigationDrawerItemClickClasses.SearchFragment;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // declaration here.........
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    InterstitialAd mInterstitialAd;
    FragmentManager fragmentManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
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

         /*  /////adddds by google
        AdView  mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
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
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }*/
//       LocaleHelper.setLocale(getApplicationContext(),"ar-TN");
//       Locale.setDefault(Locale.forLanguageTag("ar"));

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // language font working
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Shoroq-Font.ttf");
        toolbar.setTitleMarginStart(290);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.requestLayout();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        drawer.setScrimColor(R.color.textColor);

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            Fragment fragment = new CatagoryFragment();
            navigationView.getMenu().getItem(0).setChecked(true);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*   // Add code to print out the key hash
       PackageInfo info;
       try {
           info = getPackageManager().getPackageInfo("com.gulzar.usman.floginexample", PackageManager.GET_SIGNATURES);
           for (Signature signature : info.signatures) {
               MessageDigest md;
               md = MessageDigest.getInstance("SHA");
               md.update(signature.toByteArray());
               String something = new String(Base64.encode(md.digest(), 0));
               //String something = new String(Base64.encodeBytes(md.digest()));
               Log.e("hash key\n", something);
           }
       } catch (PackageManager.NameNotFoundException e1) {
           Log.e("name not found", e1.toString());
       } catch (NoSuchAlgorithmException e) {
           Log.e("no such an algorithm", e.toString());
       } catch (Exception e) {
           Log.e("exception", e.toString());
       }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
                fragment = new favourite();
                Toast.makeText(getApplication(), "Favourite has selected..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Latest:
                fragment = new LatestFragment();
                Toast.makeText(getApplicationContext(), "latest episodes are", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Ask:
                Toast.makeText(getApplicationContext(), "latest episodes are", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Report:
                Toast.makeText(getApplication(), "Report is ready to submit..", Toast.LENGTH_SHORT).show();
                fragment = new ReportBugFragment();
                break;
            case R.id.share:
                fragment = new ShareFragment();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
               /* Intent intent = new Intent(MainActivity.this,SearchFragment.class);
                startActivity(intent);*/

                Toast.makeText(getApplicationContext(), "search clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favourite:
                Intent intent = new Intent(MainActivity.this, favourite.class);
                intent.putExtra("fav", "favourite Episodes");
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "favourite clicked", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

