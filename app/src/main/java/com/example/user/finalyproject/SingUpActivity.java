package com.example.user.finalyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        final EditText kullaniciAdi=findViewById(R.id.user_name_edt);
        final EditText sifre=findViewById(R.id.password_edt);
        Button kaydol=findViewById(R.id.sing_up_btn);
        kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SingUpActivity.this,LoginActivity.class);
                intent.putExtra("username",kullaniciAdi.getText());
                intent.putExtra("password",sifre.getText());
                startActivity(intent);
            }
        });

    }
}
