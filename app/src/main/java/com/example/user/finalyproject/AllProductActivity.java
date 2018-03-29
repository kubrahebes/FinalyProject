package com.example.user.finalyproject;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.user.finalyproject.DataBase.DbHelper;
import com.example.user.finalyproject.DataBase.ProductContract;
import com.example.user.finalyproject.adapters.AllProductAdapter;


public class AllProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private int loaderKeyCode = 0;
    private DbHelper mDbHelper;
    private ListView listView;
    private AllProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);

        listView = findViewById(R.id.list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAddPage = new Intent(AllProductActivity.this, AddProductActivity.class);
                startActivity(openAddPage);
            }
        });

        mDbHelper = new DbHelper(this);
        getLoaderManager().initLoader(loaderKeyCode, null, this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AllProductActivity.this, DetailActivity.class);
                intent.putExtra("select_product", i);
                startActivity(intent);

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
        TextView textViewEmpty = findViewById(R.id.empty_list);
        if (cursor.getCount() == 0) {
            listView.setVisibility(View.GONE);
            textViewEmpty.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            textViewEmpty.setVisibility(View.GONE);
            adapter = new AllProductAdapter(this, cursor);
            listView.setAdapter(adapter);

        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
