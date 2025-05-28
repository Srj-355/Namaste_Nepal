package com.example.namastenepal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namastenepal.movie.MovieResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductVH> {
    private ArrayList<MovieResults> movieResultsList = new ArrayList<>();
    private Context mContext;
    public ProductAdapter(List<MovieResults> dataList,Context context){
        movieResultsList.clear();
         movieResultsList.addAll(dataList);
        mContext = context;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_item,parent,false);
        return new ProductVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {

        holder.txtProductName.setText(movieResultsList.get(position).getTitle());
        holder.txtProductDescription.setText(movieResultsList.get(position).getOverview());
        holder.txtProductPrice.setText(String.valueOf(movieResultsList.get(position).getReleaseDate()));
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movieResultsList.get(position).getPosterPath()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return movieResultsList.size();
    }
}
