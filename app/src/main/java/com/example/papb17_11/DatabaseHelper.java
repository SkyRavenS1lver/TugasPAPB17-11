package com.example.papb17_11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "toko-db";
    private static final  int DATABASE_VERSION = 1;
    //The table
    private static final String PRODUCT_TABLE = "productV2";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_BRAND = "brand";
    private static final String PRODUCT_DESC = "description";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PRODUCT = " CREATE TABLE " + PRODUCT_TABLE +
                "( "+PRODUCT_ID+" INTEGER PRIMARY KEY, "+
                PRODUCT_NAME+" TEXT, "+ PRODUCT_PRICE + " INTEGER, "+
            PRODUCT_BRAND+" TEXT, "+ PRODUCT_DESC+" TEXT ) ";
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void insertRecord(Product product){
        SQLiteDatabase db = getWritableDatabase();
        // Transform to content values
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_PRICE, product.getPrice());
        values.put(PRODUCT_BRAND, product.getBrand());
        values.put(PRODUCT_DESC, product.getDesc());

        //Inserting data into DB
        db.insert(PRODUCT_TABLE,null, values);
        db.close();
    }

    public Product getProductFromName(String name){
        SQLiteDatabase db = getReadableDatabase();

        // Pointer record
        Cursor cursor = db.query(PRODUCT_TABLE, new String[]{PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE},
                PRODUCT_NAME+"=?", new String[]{name},null,null,null);

        // Mengambil data pertama dari cursor
        if (cursor != null){
            cursor.moveToFirst();
        }

        // Urut berdasarkan query
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setPrice(cursor.getInt(2));
        return product;
    }

    public List<Product> getAllProducts(){
        List<Product> allProducts = new ArrayList<Product>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * from " + PRODUCT_TABLE;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getInt(2));
                product.setBrand(cursor.getString(3));
                product.setDesc(cursor.getString(4));
                allProducts.add(product);
            }while (cursor.moveToNext());
        }
        return allProducts;
    }

    public int updateProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        //Making values
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_PRICE, product.getPrice());
        values.put(PRODUCT_BRAND, product.getBrand());
        values.put(PRODUCT_DESC, product.getDesc());
        //Executing update data
        return db.update(PRODUCT_TABLE,values, PRODUCT_ID+"=?", new String[]{String.valueOf(product.getId())});
    }

    public void deleteProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PRODUCT_TABLE, PRODUCT_ID+"=?", new String[]{String.valueOf(product.getId())});
        db.close();
    }
}
