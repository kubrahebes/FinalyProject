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
        setContentView(R.layout.productdetail_activity);
        getPosition = getIntent().getIntExtra("select_product", 0);
        productNamextx = findViewById(R.id.productName);
        pricee = findViewById(R.id.price);
        quentity = findViewById(R.id.quentity);
        supportName = findViewById(R.id.supporname);
        supportEmail = findViewById(R.id.supportemail);
        supportPhone = findViewById(R.id.supportphone);
        increas = findViewById(R.id.increas);
        decreas = findViewById(R.id.decreas);
        deletebtn = findViewById(R.id.delete);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE
        };

        Cursor cursor = getContentResolver().query(ProductContract.ProductEntry.CONTENT_URI, projection, null, null, null);
        cursor.moveToPosition(getPosition);
        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int SupnameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME);
        int SupEmailColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL);
        int SupPhoneColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE);
        name = cursor.getString(nameColumnIndex);
        quantity = cursor.getInt(quantityColumnIndex);
        price = cursor.getInt(priceColumnIndex);
        supportnamee = cursor.getString(SupnameColumnIndex);
        supportEmaill = cursor.getString(SupEmailColumnIndex);
        supplierPhonee = cursor.getString(SupPhoneColumnIndex);
        productNamextx.setText("" + name);
        quentity.setText(Integer.toString(quantity));
        pricee.setText(Integer.toString(price));
        supportName.setText("" + supportnamee);
        supportEmail.setText("" + supportEmaill);
        supportPhone.setText("" + supplierPhonee);
        increas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 0) {
                    quantity -= 1;
                    ContentValues datas = new ContentValues();
                    datas.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
                    getContentResolver().update(ProductContract.ProductEntry.CONTENT_URI, datas, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{name});
                    quentity.setText(quantity + "");

                }
            }
        });

        decreas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity += 1;
                ContentValues datas = new ContentValues();
                datas.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
                getContentResolver().update(ProductContract.ProductEntry.CONTENT_URI, datas, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{name});
                quentity.setText(quantity + "");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                if (supportEmaill.isEmpty() == false) {
                    String uri = "tel:" + supplierPhonee.trim();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                update();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void delete() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DetailActivity.this);
        builder1.setMessage("Are you sure this record will be deleted.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(ProductContract.ProductEntry.CONTENT_URI, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{name});
                        finish();
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder1.create();
        alert.show();

    }

    public void update() {
        Intent intent = new Intent(this, UpdataActivity.class);
        intent.putExtra("select_product", getPosition);
        startActivity(intent);
        finish();

    }

}