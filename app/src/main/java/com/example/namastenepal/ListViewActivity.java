package com.example.namastenepal;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private ProductListAdapter adapter;
    private ArrayList<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        findView();
        initToolbar();

    }

    private void findView(){
        listView = findViewById(R.id.listView);
        setProductData();
        adapter = new ProductListAdapter(productList,ListViewActivity.this);
        listView.setAdapter(adapter);
    }
    private void initToolbar(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product List page");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setProductData(){
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

}