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

import java.util.ArrayList;


public class AllProductAdapter extends CursorAdapter {
    private Context Context1;



    public AllProductAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.Context1 = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.product_name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView priceTextView1 = view.findViewById(R.id.quentity1);
        Button sendMail= view.findViewById(R.id.mail_send);


        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int quentityColumnIndex2 = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL);

        final String productName = cursor.getString(nameColumnIndex);
        final String  price = cursor.getString(priceColumnIndex);
        final String productName1 = cursor.getString(quentityColumnIndex2);

        nameTextView.setText(productName);
        priceTextView.setText("" + price);
        priceTextView1.setText("" + productName1);

     sendMail.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent(Intent.ACTION_SEND);
             i.setType("message/rfc822");
             i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kubra.hebes@gmail.com"});
             i.putExtra(Intent.EXTRA_SUBJECT, "Urün Bilgisi");
             i.putExtra(Intent.EXTRA_TEXT   , productName+ " "+ price+ " "+ productName1);
             try {
               context.startActivity(Intent.createChooser(i, "Send mail..."));
             } catch (android.content.ActivityNotFoundException ex) {
                 Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
             }
         }
     });





    }
}

