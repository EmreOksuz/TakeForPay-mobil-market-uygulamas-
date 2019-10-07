package com.example.takeforpay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class YoneticiActivity extends AppCompatActivity {

    public TextView YöneticiName;
    public Button ürünekle;
    public Button Cikis;
    public Button StokKontrol;
    public  Yöneticiler y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici);


        YöneticiName=findViewById(R.id.AdSoyadTextView);
        ürünekle=findViewById(R.id.ÜRÜNBTN);
        Cikis=findViewById(R.id.CİKİSBTNN);
        StokKontrol=findViewById(R.id.StokBTN);

        final String gelenisim=getIntent().getStringExtra("isim");
        final String gelenp=getIntent().getStringExtra("parola");
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference YöneticilerRef=database.getReference("Yöneticiler/"+gelenisim+"/"+gelenp);
        YöneticilerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d:dataSnapshot.getChildren()) {

                    y = d.getValue(Yöneticiler.class);

                    String kod = y.getAdı();
                    String kod2 = y.getSoyadı();
                    YöneticiName.setText(kod+" "+kod2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yeniintent = new Intent(YoneticiActivity.this, LoginActivity.class);
                startActivity(yeniintent);
            }
        });
        ürünekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yeniintent = new Intent(YoneticiActivity.this, UrunEkleActivity.class);
                startActivity(yeniintent);

            }
        });
        StokKontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yeniintent = new Intent(YoneticiActivity.this, StokKontrolActivity.class);
                startActivity(yeniintent);
            }
        });





    }
}
