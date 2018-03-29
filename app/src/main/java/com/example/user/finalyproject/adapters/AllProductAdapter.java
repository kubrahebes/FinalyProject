package com.example.user.finalyproject.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.user.finalyproject.DataBase.ProductContract;
import com.example.user.finalyproject.R;


public class AllProductAdapter extends CursorAdapter {
    private Context Context1;

    TextView quentityTextView;

    public AllProductAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.Context1 = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.product_name);
        TextView priceTextView = view.findViewById(R.id.price);
        quentityTextView = view.findViewById(R.id.quentity);
        Button buybtn = view.findViewById(R.id.buy_btn);

        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int quentityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);

        final String productName = cursor.getString(nameColumnIndex);
        final int price = cursor.getInt(priceColumnIndex);
        final Integer[] quantity = {cursor.getInt(quentityColumnIndex)};

        nameTextView.setText(productName);
        priceTextView.setText("" + price);
        quentityTextView.setText("" + quantity[0]);

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
        });


    }
}

