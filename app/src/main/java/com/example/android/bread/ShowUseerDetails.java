package com.example.android.bread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bread.Models.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class ShowUseerDetails extends AppCompatActivity {


    Context _ctx;

    Realm realm;
    RealmResults<User> results;
    int id;

    TextView showName, showFlour, showBalance, showDept;
    ImageView img_back;
    Button btn_reducing_5;

    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_useer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _ctx = this;
        realm = Realm.getDefaultInstance();

        //--- int control
        showName = (TextView) findViewById(R.id.showName);
        showFlour = (TextView) findViewById(R.id.showFlour);
        showBalance = (TextView) findViewById(R.id.showBalance);
        showDept = (TextView) findViewById(R.id.showDept);
        btn_save = (Button) findViewById(R.id.btn_save);


        btn_reducing_5 = (Button) findViewById(R.id.btn_reducing_5);

        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //-- get Extras
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            id = intent.getIntExtra("id", 0);
            results = realm.where(User.class).equalTo("id", id).findAll();
            for (User user : results) {
                showName.setText(user.getName());
                showFlour.setText(user.getFlourAmount() + "");
                showBalance.setText(user.getBalance() + "");

                int dept = (user.getFlourAmount() - user.getBalance());
                showDept.setText(dept + "");
            }
        }

        btn_reducing_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                id = intent.getIntExtra("id", 0);
                results = realm.where(User.class).equalTo("id", id).findAll();
                for (User user : results) {
                    int reducing_5 = (user.getFlourAmount() - 5);
                    showFlour.setText(reducing_5 + "");

                }

            }
        });



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (User user : results) {
                    realm.beginTransaction();
                    user.setName(showName.getText().toString());
                    user.setFlourAmount(Integer.parseInt(showFlour.getText().toString()));
                    user.setBalance(Integer.parseInt(showBalance.getText().toString()));
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    finish();
                }
            }
        });

    }// end of onCreate

}
