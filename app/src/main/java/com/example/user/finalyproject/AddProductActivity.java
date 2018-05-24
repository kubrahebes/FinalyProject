package com.example.user.finalyproject;

import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.finalyproject.DataBase.DbHelper;
import com.example.user.finalyproject.DataBase.ProductContract;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddProductActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText urun_kodu;
    EditText urun_adi;
    EditText urun_cinsi;
    EditText urun_adeti;
    EditText urun_ozelligi;
    Button urun_giris_tarihi;
    Button urun_cikis_tarihi;
    SimpleDateFormat simpleDateFormat;
    String urun_cikis;
    private int loaderKeyCode = 0;
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        urun_kodu = (EditText) findViewById(R.id.urun_kod);
        urun_adi = (EditText) findViewById(R.id.urun_adi);
        urun_adeti = (EditText) findViewById(R.id.urun_adeti);
        urun_cinsi = (EditText) findViewById(R.id.urun_cins);
        urun_ozelligi = (EditText) findViewById(R.id.urun_ozellik);
        urun_giris_tarihi = (Button) findViewById(R.id.urun_gris_tarih);
        urun_cikis_tarihi = (Button) findViewById(R.id.urun_cıkıs_tarih);
        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.US);
        Button btnAdd = findViewById(R.id.urun_ekle);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
            }
        });
        urun_cikis_tarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate(1980, 0, 1, R.style.DatePickerSpinner);
            }
        });



        urun_giris_tarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate(1980, 0, 1, R.style.DatePickerSpinner);
            }
        });
    }


    public void insertdata() {

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
       /* if (urun_kodu.getText().toString().isEmpty()
                || urun_adi.getText().toString().isEmpty()
                || urun_cinsi.getText().toString().isEmpty() ||
                urun_ozelligi.getText().toString().isEmpty() ||
                urun_adeti.getText().toString().isEmpty()
                || urun_giris_tarihi.getText().toString().isEmpty()
                || urun_cikis_tarihi.getText().toString().isEmpty()){
            Toast.makeText(AddProductActivity.this, "You need to fill everywhere.", Toast.LENGTH_SHORT).show();
        }  else {*/

        ContentValues cv = new ContentValues();
        cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, urun_kodu.getText().toString());
        cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, urun_adi.getText().toString());
        cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, urun_cinsi.getText().toString());
        cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, urun_ozelligi.getText().toString());
        cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, urun_adeti.getText().toString());
        cv.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE, "");
        cv.put(ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE, urun_cikis);

        db.insert(ProductContract.ProductEntry.TABLE_NAME, null, cv);
        Toast.makeText(this, urun_adi.getText().toString(), Toast.LENGTH_SHORT).show();
        finish();
        // }

    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = new GregorianCalendar(i2, i1, i);
         urun_cikis=simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()).toString(), Toast.LENGTH_SHORT).show();
    }

    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(AddProductActivity.this)
                //.callback(AddProductActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }



}
