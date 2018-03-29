package com.example.user.finalyproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.finalyproject.DataBase.DbHelper;
import com.example.user.finalyproject.DataBase.ProductContract;

public class AddProductActivity extends AppCompatActivity {
    EditText product_name;
    EditText price;
    EditText quentity;
    EditText supportName;
    EditText supportEmail;
    EditText supportPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        product_name = (EditText) findViewById(R.id.product_name);
        price = (EditText) findViewById(R.id.price);
        quentity = (EditText) findViewById(R.id.quentity);
        supportName = (EditText) findViewById(R.id.supportName);
        supportEmail = (EditText) findViewById(R.id.supportemail);
        supportPhone = (EditText) findViewById(R.id.supportphone);
        Button btnAdd = findViewById(R.id.addnewProduct);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
            }
        });

    }

    public void insertdata() {

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (product_name.getText().toString().isEmpty() || price.getText().toString().isEmpty() || quentity.getText().toString().isEmpty() ||
                supportName.getText().toString().isEmpty() ||
                supportEmail.getText().toString().isEmpty()) {
            Toast.makeText(AddProductActivity.this, "You need to fill everywhere.", Toast.LENGTH_SHORT).show();
        } else if (isValidEmail(supportEmail.getText().toString().trim()) == false) {
            Toast.makeText(AddProductActivity.this, "You need to right your mail address correctly.", Toast.LENGTH_SHORT).show();

        } else {
            ContentValues cv = new ContentValues();
            cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, product_name.getText().toString());
            cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, price.getText().toString());
            cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quentity.getText().toString());
            cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, supportName.getText().toString());
            cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, supportEmail.getText().toString());
            cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE, supportPhone.getText().toString());

            db.insert(ProductContract.ProductEntry.TABLE_NAME, null, cv);
            finish();
        }

    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
