package com.example.user.finalyproject.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "store.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " ("
                + ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " INTEGER NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE + " TEXT NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY + " TEXT , "
                + ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME + " TEXT, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE + " TEXT ,"
                + ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL + " INTEGER, "
                + ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE + " TEXT);";
        sqLiteDatabase.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
