package com.example.namastenepal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {

    private ArrayList<Product> productList= new ArrayList<>();
    private Context context;

    public ProductListAdapter(ArrayList<Product> dataList,Context mContext){
        productList.clear();
        productList.addAll(dataList);
        context=mContext;
    }

    @Override
    public int getCount() {
         return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_list_item,null);
        ProductViewHolder productViewHolder= new ProductViewHolder(view);
        productViewHolder.txtProductName.setText(productList.get(position).getName());
        productViewHolder.txtProductDescription.setText(productList.get(position).getDescription());
        productViewHolder.txtProductPrice.setText(String.valueOf(productList.get(position).getPrice()));
        productViewHolder.imgProduct.setImageDrawable(context.getDrawable(productList.get(position).getImage()));

        return view;
    }
    public static class ProductViewHolder{
        ImageView imgProduct;
        TextView txtProductName,txtProductDescription,txtProductPrice;
        public ProductViewHolder(View view){
            imgProduct=view.findViewById(R.id.imgProduct);
            txtProductName=view.findViewById(R.id.txtProductName);
            txtProductDescription= view.findViewById(R.id.txtProductDescription);
            txtProductPrice=view.findViewById(R.id.txtProductPrice);
        }
    }

}
