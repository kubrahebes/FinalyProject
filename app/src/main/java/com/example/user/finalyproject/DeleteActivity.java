package com.example.user.finalyproject;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.finalyproject.DataBase.DbHelper;
import com.example.user.finalyproject.DataBase.ProductContract;
import com.example.user.finalyproject.adapters.AllProductAdapter3;

public class DeleteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {
    private int loaderKeyCode = 0;
    private DbHelper mDbHelper;
    private ListView listView;
    private AllProductAdapter3 adapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        listView = findViewById(R.id.list);



        mDbHelper = new DbHelper(this);
        getLoaderManager().initLoader(loaderKeyCode, null, DeleteActivity.this);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
                cursor.moveToPosition(i);
                int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
                name = cursor.getString(nameColumnIndex);

                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle(R.string.delete);
                builder.setMessage(R.string.context);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                           getContentResolver().delete(ProductContract.ProductEntry.CONTENT_URI, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{name});
                        Toast.makeText(DeleteActivity.this, "Ürününüz Silindi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DeleteActivity.this, DeleteActivity.class);
                        startActivity(intent);
                        finish();

                    }

                })
                        .setNegativeButton(R.string.no, null)
                        .show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                deleteAll();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteAll() {
        int rowsDeleted = getContentResolver().delete(ProductContract.ProductEntry.CONTENT_URI, null, null);
    }

    protected void onResume() {

        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE};

        return new CursorLoader(this, ProductContract.ProductEntry.CONTENT_URI, projection, null,
                null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //  cursor.moveToFirst();

        adapter = new AllProductAdapter3
                (this, cursor);
        listView.setAdapter(adapter);



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
