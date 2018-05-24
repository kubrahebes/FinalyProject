package com.example.user.finalyproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpleshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SpleshActivity.this,LoginActivity.class);
                intent.putExtra("username","kubrahebes");
                intent.putExtra("password","1234");
                startActivity(intent);
            }
        }, 2500);

    }
}
