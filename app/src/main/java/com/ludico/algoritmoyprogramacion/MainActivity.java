package com.ludico.algoritmoyprogramacion;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = (WebView) findViewById(R.id.home);
        home.getSettings().setJavaScriptEnabled(true);
        BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/home");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/ejercicios");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void BorrarYCargarWeb(String web) {
        home.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                home.loadUrl("javascript:(function() { " +
                        "document.getElementById('sites-chrome-sidebar-left').style.display='none';})()");
                home.loadUrl("javascript:(function() { " +
                        "document.getElementById('sites-chrome-header-wrapper').style.display='none';})()");
            }
        });
        home.loadUrl(web);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.paginaP) {
            BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/home");
        } else if (id == R.id.clases) {
            BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/clases");
        } else if (id == R.id.ejercicios) {
            BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/ejercicios");
        } else if (id == R.id.material) {
            BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/material-complementario");
        } else if (id == R.id.tps) {
            BorrarYCargarWeb("https://sites.google.com/site/algoritmosyprogramacionii2017/trabajos-practicos");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
