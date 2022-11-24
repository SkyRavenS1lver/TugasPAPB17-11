package com.example.papb17_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText mNameEditText;
    EditText mPriceEditText;
    Button mInsertBtn;
    EditText mKeywordEditText;
    Button mSearchBtn;
    TextView mPriceResult;
    Button mGetAll;
    TextView mHasilAll;

    public static DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.ET_Name);
        mPriceEditText = findViewById(R.id.ET_Price);
        mInsertBtn = findViewById(R.id.btn_insert);
        mKeywordEditText = findViewById(R.id.UserInput);
        mSearchBtn = findViewById(R.id.btn_search);
        mPriceResult = findViewById(R.id.hasilHarga);
        mGetAll = findViewById(R.id.btn_searchAll);
        mHasilAll = findViewById(R.id.hasilSemua);
        mDatabaseHelper = new DatabaseHelper(this);

        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPriceFromName(mKeywordEditText.getText().toString());
            }
        });
        mGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllData();
            }
        });
    }

    private void showAllData() {
        List<Product> productList = mDatabaseHelper.getAllProducts();
        String result = "";
        for (int i = 0; i<productList.size(); i++){
            Product product = productList.get(i);
            result = result + product.getName()+", ";
        }
        mHasilAll.setText(result);
    }

    private void insertData() {
        Product product = new Product();
        product.setName(mNameEditText.getText().toString());
        product.setPrice(Integer.valueOf(mPriceEditText.getText().toString()));
        mDatabaseHelper.insertRecord(product);
        Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
    }

    public void showPriceFromName(String keyword){
        Product product = mDatabaseHelper.getProductFromName(keyword);
        mPriceResult.setText(String.valueOf(product.getPrice()));
    }
}
