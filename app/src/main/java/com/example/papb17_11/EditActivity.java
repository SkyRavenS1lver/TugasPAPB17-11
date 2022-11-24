package com.example.papb17_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent getter = getIntent();
        int productId = getter.getIntExtra("id", -1);
        String nama = getter.getStringExtra("name");
        String brand = getter.getStringExtra("brand");
        String price = getter.getStringExtra("price");
        String desc = getter.getStringExtra("desc");
        ((TextView)findViewById(R.id.productName)).setText(nama);
        ((TextView)findViewById(R.id.productBrand)).setText(brand);
        ((TextView)findViewById(R.id.productPrice)).setText(price);
        ((TextView)findViewById(R.id.productContent)).setText(desc);
        ((FloatingActionButton) findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(EditActivity.this, ProductDetail.class);
                intent.putExtra("name", nama);
                intent.putExtra("brand", brand);
                intent.putExtra("price", price);
                intent.putExtra("id", productId);
                intent.putExtra("desc", desc);
                startActivity(intent);
            }
        });
        ((FloatingActionButton) findViewById(R.id.confirmButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
