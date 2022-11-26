package com.example.papb17_11.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.papb17_11.ProductRelated.Product;
import com.example.papb17_11.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductDetail extends AppCompatActivity {
    FloatingActionButton edit,confirm,cancel,back;
    EditText name,brand,price,description;
    TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        back = findViewById(R.id.backButton);
        edit = findViewById(R.id.editData);
        confirm = findViewById(R.id.confirmButton);
        cancel = findViewById(R.id.cancelButton);
        name = findViewById(R.id.productName);
        brand = findViewById(R.id.productBrand);
        price = findViewById(R.id.productPrice);
        description = findViewById(R.id.productContent);
        Intent getter = getIntent();
        judul = findViewById(R.id.Judul);
        judul.setText(getter.getStringExtra("judul"));
        Intent intent = new Intent(ProductDetail.this,ReadDataActivity.class);
        String mode = getter.getStringExtra("mode");
        if(mode.equals("detail")) {
            changeState(false);
            int productId = getter.getIntExtra("id", 0);
            name.setText(getter.getStringExtra("name"));
            brand.setText(getter.getStringExtra("brand"));
            price.setText(getter.getStringExtra("price"));
            description.setText(getter.getStringExtra("desc"));
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    judul.setText("Edit Data");
                    changeState(true);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Product product = ReadDataActivity.mDatabaseHelper.getProductFromId(productId);
                            product.setName(name.getText().toString());
                            product.setPrice(Integer.parseInt(price.getText().toString()));
                            product.setBrand(brand.getText().toString());
                            product.setDesc(description.getText().toString());
                            ReadDataActivity.mDatabaseHelper.updateProduct(product);
                            changeState(false);
                            judul.setText("Detail Data");

                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            changeState(false);
                            judul.setText("Detail Data");
                        }
                    });
                }
            });
        }
        else if(mode.equals("add")){
            changeState(true);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Product product = new Product();
                    product.setName(name.getText().toString());
                    product.setPrice(Integer.parseInt(price.getText().toString()));
                    product.setBrand(brand.getText().toString());
                    product.setDesc(description.getText().toString());
                    ReadDataActivity.mDatabaseHelper.insertRecord(product);
                    startActivity(intent);
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
    public void changeState(Boolean bool){
        if (bool){
            edit.setVisibility(View.INVISIBLE);
            back.setVisibility(View.INVISIBLE);
            confirm.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
        }
        else{
            edit.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.INVISIBLE);
            cancel.setVisibility(View.INVISIBLE);
        }
        name.setFocusable(bool);
        name.setFocusableInTouchMode(bool);
        brand.setFocusable(bool);
        brand.setFocusableInTouchMode(bool);
        price.setFocusable(bool);
        price.setFocusableInTouchMode(bool);
        description.setFocusable(bool);
        description.setFocusableInTouchMode(bool);
    }
}
