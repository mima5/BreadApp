package com.example.android.bread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.bread.Models.User;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import Adapters.userAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity";

   Context _ctx;

    Realm realm;
    RealmResults<User> arrayList;

    private RecyclerView rv;
    userAdapter adapter;
    public float val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent = new Intent(_ctx,AddUser.class);
                startActivity(intent);
            }
        });

        //-- int controls


//        -- int realm
        realm = Realm.getDefaultInstance();

        RealmResults<User> userList = realm.where(User.class).findAll();
        rv = findViewById(R.id.rv_user);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new GridLayoutManager(_ctx, 1));

        rv.addItemDecoration(new DividerItemDecoration(_ctx,
                DividerItemDecoration.VERTICAL));


        adapter = new userAdapter(userList,this);
        rv.setAdapter(adapter);

//--------------------------------

        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.action_search);


//-----------------------------




//        final FluidSlider slider = findViewById(R.id.fluidSlider);
//        slider.setBeginTrackingListener(new Function0<Unit>() {
//            @Override
//            public Unit invoke() {
////               val = Integer.parseInt(String.valueOf(slider.getPosition()));
//                Log.d("D", "setBeginTrackingListener");
//                return Unit.INSTANCE;
//            }
//        });
//
//        slider.setEndTrackingListener(new Function0<Unit>() {
//            @Override
//            public Unit invoke() {
//                Log.d("D", "setEndTrackingListener");
//                return Unit.INSTANCE;
//            }
//        });

        //-- int realm
//        realm = Realm.getDefaultInstance();
//
//        realm.beginTransaction();
//        realm.deleteAll();
//        realm.commitTransaction();



        //-- navigationView
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//        newText = newText.toLowerCase();
//        List<User> users = new ArrayList<>();
//
//        for (User offer : arrayList) {
//            String name = offer.getName().toLowerCase();
//            if (name.contains(newText))
//                users.add(offer);
//
//        }
//        adapter.setFilter(users);
//
//        return true;
//    }



}// end of class
