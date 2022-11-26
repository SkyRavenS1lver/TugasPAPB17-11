package com.example.papb17_11.ProductRelated;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.papb17_11.Activity.ProductDetail;
import com.example.papb17_11.Activity.ReadDataActivity;
import com.example.papb17_11.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> values;
    private final LayoutInflater inflater;

    public ProductAdapter(@NonNull Context context, ArrayList<Product> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product =values.get(position);
        holder.brand.setText(product.getBrand());
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),"Product "+product.getName()+" Terhapus", Toast.LENGTH_SHORT).show();
                ReadDataActivity.productArrayList.remove(product);
                ReadDataActivity.adapter.notifyDataSetChanged();
                ReadDataActivity.dataChange();
                ReadDataActivity.mDatabaseHelper.deleteProduct(product);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(holder.itemView.getContext(), ProductDetail.class);
                intent.putExtra("name", product.getName());
                intent.putExtra("judul", "Detail Data");
                intent.putExtra("mode", "detail");
                intent.putExtra("brand", product.getBrand());
                intent.putExtra("price", String.valueOf(product.getPrice()));
                intent.putExtra("id", product.getId());
                intent.putExtra("desc", product.getDesc());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (values == null){
            return 0;
        };
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView brand;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            brand = itemView.findViewById(R.id.productBrand);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
