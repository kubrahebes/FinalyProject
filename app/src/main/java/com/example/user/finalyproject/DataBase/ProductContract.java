package com.example.user.finalyproject.DataBase;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ProductContract {
    public static final String CONTENT_AUTHORITY = "com.example.user.finalyproject";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCTS = "Product";


    ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;


        public static final String TABLE_NAME = "products";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "productName";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_IMAGE = "image";
        public static final String COLUMN_SUPPLIER_NAME = "supplierName";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplierEmail";
        public static final String COLUMN_SUPPLIER_PHONE = "supplierPhone";
    }

}
