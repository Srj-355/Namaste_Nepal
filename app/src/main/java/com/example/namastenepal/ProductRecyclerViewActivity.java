package com.example.namastenepal;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namastenepal.database.DBHandler;
import com.example.namastenepal.movie.MovieContainer;
import com.example.namastenepal.movie.MovieResults;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductRecyclerViewActivity extends AppCompatActivity {
    private final ArrayList<Product> productList = new ArrayList<>();
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private MovieContainer movieData;
    private ArrayList<MovieResults> movieResultList = new ArrayList<>();
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_recycler_view);
        initToolbar();
        getName();
        dbHandler = new DBHandler(this);
        JSONAsyncTask jsonAsyncTask = new JSONAsyncTask();
        jsonAsyncTask.execute();
        final Handler handler = new Handler(Looper.getMainLooper());
        findView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                findView();
            }
        }, 5000);
    }

    private void findView() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProductAdapter(movieResultList, ProductRecyclerViewActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product Recycler page");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("BCA6th", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", "Saroj");
        editor.putInt("Data", 33);
        editor.apply();

    }

    private void getName() {
        SharedPreferences sharedPreferences = getSharedPreferences("BCA6th", MODE_PRIVATE);
        String Name = sharedPreferences.getString("Name", "");
        int Data = sharedPreferences.getInt("Data", 0);
    }

    private void setProductData() {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        Product p4 = new Product();

        p1.setName("Rimac Mechanical keyboard set");
        p1.setDescription("gajab xa keyboard");
        p1.setPrice(5000);
        p1.setImage(R.drawable.signuppagebg);

        p2.setName("Taro GP Bluetooth Headset");
        p2.setDescription("gajab xa headset");
        p2.setPrice(50000);
        p2.setImage(R.drawable.signuppagebg);

        p3.setName("Tencent gp Air 3 pods pro");
        p3.setDescription("air pod is gajab ");
        p3.setPrice(15000);
        p3.setImage(R.drawable.signuppagebg);

        p4.setName("Casio watch limited edition Royal segment");
        p4.setDescription("Elite watch set for men with golden aura ");
        p4.setPrice(6600055);
        p4.setImage(R.drawable.signuppagebg);

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
    }

    class JSONAsyncTask extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog progressDialog = new ProgressDialog(ProductRecyclerViewActivity.this);

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("please wait...");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.themoviedb.org/3/movie/upcoming?api_key=ef8f48b43832a9e95b87408bf739ed51").build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("Error: ", String.valueOf(e.getLocalizedMessage()));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        String jsonObject = response.body().string();
                        Gson gsonObject = new Gson();
                        movieData = gsonObject.fromJson(jsonObject, MovieContainer.class);
                        dbHandler.deleteMovieData();
                        for (int i = 0; i < movieData.getResultsList().size(); i++) {
                            dbHandler.addMovieData(movieData.getResultsList().get(i).getOriginalTitle(), movieData.getResultsList().get(i).getOverview(), movieData.getResultsList().get(i).getReleaseDate(), movieData.getResultsList().get(i).getPosterPath());
                        }
                        movieResultList.clear();
                        movieResultList.addAll(movieData.getResultsList());
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }
}