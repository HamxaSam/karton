package com.example.hamza.karton;

import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.Locale;

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

//        LocaleHelper.setLocale(getApplicationContext(),"ar-TN");
//        Locale.setDefault(Locale.forLanguageTag("ar"));

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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //on navigational item click
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        //item.setChecked(true);
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
                Toast.makeText(getApplication(),"latest has selected..",Toast.LENGTH_SHORT).show();
                // fragment = new facebook_activity();
                break;
            case R.id.Report:
                Toast.makeText(getApplication(),"Report has selected..",Toast.LENGTH_SHORT).show();
                //fragment = new facebook_activity();
                break;
            case R.id.share:
                Toast.makeText(getApplication(),"Share has selected..",Toast.LENGTH_SHORT).show();
                fragment = new ShareFragment();
                break;
            case R.id.Rate:
                Toast.makeText(getApplication(),"Catagory has selected..",Toast.LENGTH_SHORT).show();
                // fragment = new facebook_activity();
                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(getApplicationContext(), "favourite clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favourite:
                Intent intent = new Intent(MainActivity.this, favourite.class);
                intent.putExtra("fav", "favourite Episodes");
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "searching clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
