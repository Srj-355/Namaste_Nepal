package com.example.namastenepal;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class ForgetpassActivity extends AppCompatActivity {
    private TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgetpassword);
        findView();
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to redirect from one page to another page
                Intent intent = new Intent(ForgetpassActivity.this,LoginActivity.class);
                startActivity(intent);
 //clears the stack data and exits the app
            }
        });

    }

    private void findView(){
        txtLogin=findViewById(R.id.txtLogin);
    }
}