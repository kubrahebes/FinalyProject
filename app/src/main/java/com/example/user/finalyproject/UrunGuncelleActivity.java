package com.example.user.finalyproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.finalyproject.DataBase.ProductContract;

public class UrunGuncelleActivity extends AppCompatActivity {
    EditText urun_kodu;
    EditText urun_adi;
    EditText urun_adeti;
    EditText urun_cinsi;
    EditText urun_ozelligi;


    String price;
    String name;
    String supportnamee;
    private String  quantity;
    String supportEmaill;
    String supplierPhonee;
    int getPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_guncelle);
        getPosition = getIntent().getIntExtra("select_product", 0);

        urun_kodu = (EditText) findViewById(R.id.urun_kod);
        urun_adi = (EditText) findViewById(R.id.urun_adi);
        urun_adeti = (EditText) findViewById(R.id.urun_adeti);
        urun_cinsi = (EditText) findViewById(R.id.urun_cins);
        urun_ozelligi = (EditText) findViewById(R.id.urun_ozellik);
        Button btnUpdate= findViewById(R.id.guncelle);

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
        quantity = cursor.getString(quantityColumnIndex);
        price = cursor.getString(priceColumnIndex);
        supportnamee = cursor.getString(SupnameColumnIndex);
        supportEmaill = cursor.getString(SupEmailColumnIndex);
        supplierPhonee = cursor.getString(SupPhoneColumnIndex);


        urun_kodu.setText("" + name);
        urun_adi.setText((price));
        urun_adeti.setText(supportEmaill);
        urun_cinsi.setText("" + quantity);
        urun_ozelligi.setText("" + supportnamee);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (urun_kodu.getText().toString().isEmpty() || urun_adi.getText().toString().isEmpty() || urun_adeti.getText().toString().isEmpty() ||
                        urun_cinsi.getText().toString().isEmpty() ||
                        urun_ozelligi.getText().toString().isEmpty()) {
                    Toast.makeText(UrunGuncelleActivity.this, "You need to fill everywhere.", Toast.LENGTH_SHORT).show();
                }  else {

                    ContentValues cv = new ContentValues();
                    cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, urun_kodu.getText().toString());
                    cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, urun_adi.getText().toString());
                    cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, urun_cinsi.getText().toString());
                    cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, urun_ozelligi.getText().toString());
                    cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, urun_adeti.getText().toString());

                    getContentResolver().update(ProductContract.ProductEntry.CONTENT_URI, cv, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{name});
                    finish();

                }


            }
        });

    }
}
