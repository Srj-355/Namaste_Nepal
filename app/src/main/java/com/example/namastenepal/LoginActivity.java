package com.example.namastenepal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edtEmail, edtPassword;
    private AppCompatButton btnLogin;

    private TextView txtSignupLink, txtForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        findView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempEmail = "aa";
                String tempPass = "aa";
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email address cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password address cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (email.equals(tempEmail) && password.equals(tempPass)) {
//                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    Intent intent = new Intent(LoginActivity.this, ProductRecyclerViewActivity.class);
                    intent.putExtra("data",email);
//                    signupActivityResults.launch(intent);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email: " + email + " " + "Password: " + password, Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to redirect from one page to another page
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.putExtra("Email", edtEmail.getText().toString());
                intent.putExtra("data", "signup");
                startActivity(intent);
              //clears the stack data and exits the app
            }
        });

        txtForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to redirect from one page to another page
                Intent intent = new Intent(LoginActivity.this, ForgetpassActivity.class);
                startActivity(intent);
//clears the stack data and exits the app finish();
            }
        });
    }

    ActivityResultLauncher<Intent> signupActivityResults = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        //there are no request code
                        Intent data = result.getData();
                        Toast.makeText(LoginActivity.this,data.getStringExtra("Result"),Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    private void findView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignupLink = findViewById(R.id.txtSignupLink);
        txtForget = findViewById(R.id.txtForget);
    }
    private void initToolbar(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login page");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.setting){
            Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.person) {
            Toast.makeText(this,"person",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.project){
            Toast.makeText(this,"project",Toast.LENGTH_SHORT).show();
        }
        else if (id==android.R.id.home) {
            Toast.makeText(this,"Back Button Clicked",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}