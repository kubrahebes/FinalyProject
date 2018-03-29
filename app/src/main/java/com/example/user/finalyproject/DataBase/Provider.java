package com.example.user.finalyproject.DataBase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class Provider extends ContentProvider {
    public static final int PRODUCTS = 100;
    private static final int PRODUCT_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_PRODUCTS, PRODUCTS);
        sUriMatcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
    }

    private DbHelper mdbHelper;

    @Override
    public boolean onCreate() {
        mdbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                cursor = db.query(ProductContract.ProductEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;
            case PRODUCT_ID:
                s = ProductContract.ProductEntry._ID + "=?";
                strings1 = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(ProductContract.ProductEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return ProductContract.ProductEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return ProductContract.ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return insertProduct(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mdbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                rowsDeleted = db.delete(ProductContract.ProductEntry.TABLE_NAME, s, strings);
                break;
            case PRODUCT_ID:
                s = ProductContract.ProductEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = db.delete(ProductContract.ProductEntry.TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("You cannot delete " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        if (contentValues.containsKey(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME)) {
            String name = contentValues.getAsString(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Enter the product name");
            }
        }

        if (contentValues.containsKey(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY)) {
            Integer quantity = contentValues.getAsInteger(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
            if (quantity != null && quantity < 0) {
                throw new IllegalArgumentException("enter the quentity");
            }
        }

        if (contentValues.containsKey(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE)) {
            Integer price = contentValues.getAsInteger(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
            if (price != null && price < 0) {
                throw new IllegalArgumentException("price will be correct");
            }
        }

        if (contentValues.size() == 0) {
            return 0;
        }

        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        int rowsUpdated = db.update(ProductContract.ProductEntry.TABLE_NAME, contentValues, s, strings);
        if (rowsUpdated == -1) {
            Toast.makeText(getContext(), "Update is not succesful", Toast.LENGTH_SHORT).show();
            return 0;
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    private Uri insertProduct(Uri uri, ContentValues values) {
        String name = values.getAsString(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Enter the product name");
        }
        Integer quantity = values.getAsInteger(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        if (quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Enter the quantıty");
        }
        Integer price = values.getAsInteger(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);

        if (price != null && price < 0) {
            throw new IllegalArgumentException("Product price should be valid");
        }

        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        long id = db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Toast.makeText(getContext(), "Insert ıs not sucssesful", Toast.LENGTH_SHORT).show();
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }
}
