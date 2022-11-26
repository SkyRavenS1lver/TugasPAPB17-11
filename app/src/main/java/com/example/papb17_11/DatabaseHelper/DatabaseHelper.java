package com.example.papb17_11.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.papb17_11.ProductRelated.Product;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "toko-dbV3";
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
                "( "+PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
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

    public Product getProductFromId(int id){
        SQLiteDatabase db = getReadableDatabase();
        // Pointer record
        Cursor cursor = db.query(PRODUCT_TABLE, new String[]{PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_BRAND, PRODUCT_DESC},
                PRODUCT_ID+"=?", new String[]{String.valueOf(id)},null,null,null);

        // Mengambil data pertama dari cursor
        if (cursor != null){
            cursor.moveToFirst();
        }

        // Urut berdasarkan query
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setPrice(cursor.getInt(2));
        product.setBrand(cursor.getString(3));
        product.setDesc(cursor.getString(4));
        return product;
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> allProducts = new ArrayList<Product>();
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

    public void updateProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        //Making values
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_PRICE, product.getPrice());
        values.put(PRODUCT_BRAND, product.getBrand());
        values.put(PRODUCT_DESC, product.getDesc());
        //Executing update data
        db.update(PRODUCT_TABLE,values, PRODUCT_ID+" = ?", new String[]{String.valueOf(product.getId())});
    }

    public void deleteProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PRODUCT_TABLE, PRODUCT_ID+"=?", new String[]{String.valueOf(product.getId())});
        db.close();
    }
}
