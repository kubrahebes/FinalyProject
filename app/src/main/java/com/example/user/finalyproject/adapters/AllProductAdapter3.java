package com.example.user.finalyproject.adapters;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.finalyproject.DataBase.ProductContract;
import com.example.user.finalyproject.R;


public class AllProductAdapter3 extends CursorAdapter {
    private Context Context1;



    public AllProductAdapter3(Context context, Cursor c) {
        super(context, c, 0);
        this.Context1 = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.stok_bilgi_item2, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.product_name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView priceTextView1 = view.findViewById(R.id.quentity1);


        //quentityTextView = view.findViewById(R.id.quentity);
//        Button buybtn = view.findViewById(R.id.buy_btn);

        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        //int quentityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int quentityColumnIndex2 = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL);

        final String productName = cursor.getString(nameColumnIndex);
        final String  price = cursor.getString(priceColumnIndex);
       // final Integer[] quantity = {cursor.getInt(quentityColumnIndex)};
        final String productName1 = cursor.getString(quentityColumnIndex2);

        nameTextView.setText(productName);
        priceTextView.setText("" + price);
       // quentityTextView.setText("" + quantity[0]);
        priceTextView1.setText("" + productName1);



/*
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity[0] > 0) {
                    quantity[0] -= 1;
                    ContentValues datas = new ContentValues();
                    datas.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity[0]);
                    view.getContext().getContentResolver().update(ProductContract.ProductEntry.CONTENT_URI, datas, ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + "=?", new String[]{productName});
                    quentityTextView.setText(quantity[0] + "");
                }
            }
        });*/


    }
}

