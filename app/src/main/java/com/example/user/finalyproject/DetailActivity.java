package com.example.user.finalyproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.finalyproject.DataBase.ProductContract;


public class DetailActivity extends AppCompatActivity {
    TextView productNamextx;
    TextView pricee;
    TextView quentity;
    TextView supportName;
    TextView supportEmail;
    TextView supportPhone;
    Button increas;
    Button decreas;
    Button deletebtn;
    int price;
    String name;
    String supportnamee;
    private Integer quantity;
    String supportEmaill;
    String supplierPhonee;
    int getPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

}