package com.example.hamza.karton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hamza.karton.Fragments.ActivityShareFragment;
import com.example.hamza.karton.Fragments.CatagoryFragment;
import com.example.hamza.karton.R;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
  // declaration here.........
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    FragmentManager fragmentManager;
  // on back press nevigation drawer
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);



//       LocaleHelper.setLocale(getApplicationContext(),"ar-TN");
//       Locale.setDefault(Locale.forLanguageTag("ar"));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            Fragment fragment = new CatagoryFragment();
            navigationView.getMenu().getItem(0).setChecked(true);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

    }

    @Override
    //on navigational item click
    public boolean onNavigationItemSelected( MenuItem item) {
       /* if (item == null && item.getItemId() == android.R.id.home) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
            return false;
        }*/

        int id = item.getItemId();
        Fragment fragment = null;
        switch (id) {
            case R.id.Catagory:
                fragment = new CatagoryFragment();
                break;
            case R.id.Search:
                Toast.makeText(getApplication(),"search has selected..",Toast.LENGTH_SHORT).show();
                //  fragment = new Frafment_Calculater_activity();
                break;
            case R.id.Favorite:
                Toast.makeText(getApplication(),"Favorite has selected..",Toast.LENGTH_SHORT).show();
                // fragment = new colorPicker_activity();
                break;

            case R.id.Latest:
                fragment = new CatagoryFragment();
                break;
            case R.id.Report:
                Toast.makeText(getApplication(),"Report has selected..",Toast.LENGTH_SHORT).show();
                //fragment = new facebook_activity();
                break;
            case R.id.share:


                fragment = new ActivityShareFragment();

                String message = "Text I want to share.";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
                Toast.makeText(getApplication(),"Share has selected..",Toast.LENGTH_SHORT).show();
                // fragment = new ShareFragment();

                break;
            case R.id.Rate:
                Toast.makeText(getApplication(),"Catagory has selected..",Toast.LENGTH_SHORT).show();
                // fragment = new facebook_activity();
                break;
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();
        navigationView.setCheckedItem(id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_drawer,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }*/
}
