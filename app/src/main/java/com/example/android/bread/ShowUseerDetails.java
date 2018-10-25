package com.example.android.bread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.android.bread.Models.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class ShowUseerDetails extends AppCompatActivity {


    Context _ctx;

    Realm realm;
    RealmResults<User> results;
    int id;

    TextView showName,showFlour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_useer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _ctx = this;
        realm = Realm.getDefaultInstance();

        showName = (TextView) findViewById(R.id.showName);
        showFlour = (TextView) findViewById(R.id.showFlour);

        //-- get Extras
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
//            fromAdapter = true;
            id = intent.getIntExtra("id", 0);
            results = realm.where(User.class).equalTo("id", id).findAll();
            for (User user : results) {
                showName.setText(user.getName());
                showFlour.setText(user.getFlourAmount()+"");
            }
        }






    }// end of onCreate

}
