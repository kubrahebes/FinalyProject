package com.example.user.finalyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainPageActivity extends AppCompatActivity {

LinearLayout urun_ekle,urun_sil,stok_bilgi,guncelle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

urun_sil =findViewById(R.id.urun_sil);
urun_ekle=findViewById(R.id.urun_ekle);
stok_bilgi=findViewById(R.id.stok_bilgi);
guncelle=findViewById(R.id.guncelle);

urun_ekle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent =new Intent (MainPageActivity.this, AddProductActivity.class);
        startActivity(intent);
    }
});

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (MainPageActivity.this, UpdataActivity.class);
                startActivity(intent);
            }
        });

        stok_bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (MainPageActivity.this, AllProductActivity.class);
                startActivity(intent);
            }
        });
        urun_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (MainPageActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
