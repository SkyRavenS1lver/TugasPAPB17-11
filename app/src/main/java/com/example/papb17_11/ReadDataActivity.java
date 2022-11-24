package com.example.papb17_11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReadDataActivity extends AppCompatActivity {
    public static DatabaseHelper mDatabaseHelper;

    TextView message;
    RecyclerView rvProduct;
    FloatingActionButton addData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        //CreateHelper
        mDatabaseHelper = new DatabaseHelper(this);
        message = findViewById(R.id.messages);
        rvProduct = findViewById(R.id.rvProduct);
        addData = findViewById(R.id.addData);




    }
}
