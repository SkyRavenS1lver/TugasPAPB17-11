package com.example.papb17_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent getter = getIntent();
        int productId = getter.getIntExtra("id", -1);
        ((TextView)findViewById(R.id.productName)).setText(getter.getStringExtra("name"));
        ((TextView)findViewById(R.id.productBrand)).setText(getter.getStringExtra("brand"));
        ((TextView)findViewById(R.id.productPrice)).setText(getter.getStringExtra("price"));
        ((TextView)findViewById(R.id.productContent)).setText(getter.getStringExtra("desc"));
        ((FloatingActionButton) findViewById(R.id.editData)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        ((FloatingActionButton) findViewById(R.id.deleteData)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
