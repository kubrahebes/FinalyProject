package com.example.user.finalyproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.user.finalyproject.DataBase.ProductContract;

public class UpdataActivity extends AppCompatActivity {
    EditText product_name;
    EditText pricee;
    EditText quentity;
    EditText supportName;
    EditText supportEmail;
    EditText supportPhone;

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
        setContentView(R.layout.activity_updata);
        getPosition = getIntent().getIntExtra("select_product", 0);

        product_name = (EditText) findViewById(R.id.product_name);
        pricee = (EditText) findViewById(R.id.price);
        quentity = (EditText) findViewById(R.id.quentity);
        supportName = (EditText) findViewById(R.id.supportName);
        supportEmail = (EditText) findViewById(R.id.supportemail);
        supportPhone = (EditText) findViewById(R.id.supportphone);
        Button btnUpdate = findViewById(R.id.addnewProduct);


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


        product_name.setText("" + name);
        quentity.setText(Integer.toString(quantity));
        pricee.setText(Integer.toString(price));
        supportName.setText("" + supportnamee);
        supportEmail.setText("" + supportEmaill);
        supportPhone.setText("" + supplierPhonee);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product_name.getText().toString().isEmpty() || pricee.getText().toString().isEmpty() || quentity.getText().toString().isEmpty() ||
                        supportName.getText().toString().isEmpty() ||
                        supportEmail.getText().toString().isEmpty()) {
                    Toast.makeText(UpdataActivity.this, "You need to fill everywhere.", Toast.LENGTH_SHORT).show();
                } else if (isValidEmail(supportEmail.getText().toString().trim()) == false) {
                    Toast.makeText(UpdataActivity.this, "You need to right your mail address correctly.", Toast.LENGTH_SHORT).show();

                } else {

                    ContentValues values = new ContentValues();
                    values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, product_name.getText().toString());
                    values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quentity.getText().toString());
                    values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, pricee.getText().toString());
                    values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, supportName.getText().toString());
                    values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE, supportPhone.getText().toString());
                    values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, supportEmail.getText().toString());
                    getContentResolver().update(ProductContract.ProductEntry.CONTENT_URI, values, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{name});
                    finish();

                }


            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
