package com.example.user.finalyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    String kullaniciAdi, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButon = findViewById(R.id.sing_in_btn);
        Button signUpButon = findViewById(R.id.sing_up_btn);
        final EditText username = findViewById(R.id.user_name_edt);
        final EditText sifre = findViewById(R.id.password_edt);

        Intent intent = getIntent();
        kullaniciAdi = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainPageActivity.class));

            }
        });
signUpButon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(LoginActivity.this, SingUpActivity.class));
    }
});

    }
}
