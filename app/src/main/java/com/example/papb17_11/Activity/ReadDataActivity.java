package com.example.papb17_11.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.papb17_11.DatabaseHelper.DatabaseHelper;
import com.example.papb17_11.ProductRelated.Product;
import com.example.papb17_11.ProductRelated.ProductAdapter;
import com.example.papb17_11.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReadDataActivity extends AppCompatActivity {
    public static DatabaseHelper mDatabaseHelper;
    public static ArrayList<Product> productArrayList;
    public static TextView message;
    RecyclerView rvProduct;
    FloatingActionButton addData;
    public static ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        //CreateHelper
        mDatabaseHelper = new DatabaseHelper(this);
        message = findViewById(R.id.messages);
        rvProduct = findViewById(R.id.rvProduct);
        addData = findViewById(R.id.addData);
        productArrayList = mDatabaseHelper.getAllProducts();
        dataChange();
        adapter = new ProductAdapter(this,productArrayList);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        rvProduct.setAdapter(adapter);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadDataActivity.this, ProductDetail.class);
                intent.putExtra("mode", "add");
                intent.putExtra("judul", "Add Data");
                startActivity(intent);
            }
        });
    }
   public static void dataChange() {
       if (productArrayList.size() > 0){
           message.setVisibility(View.INVISIBLE);
       }
       else {message.setVisibility(View.VISIBLE);}
    }

}
