package com.example.takeforpay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private Button btnGiris;
    private Button btnKayit;
    private Button btnCikis;
    public  EditText kullanıcı;
    public  EditText parola;
    public  int i=1;
    public Müsteriler müs;
    public Yöneticiler yön;

    public CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kullanıcı=findViewById(R.id.kullaniciText);
        parola=findViewById(R.id.parolaText);
        btnGiris=findViewById(R.id.girisbtn);
        check=findViewById(R.id.YöneticicheckBox);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String kullanıcıText=kullanıcı.getText().toString();
                final String parolaText=parola.getText().toString();
                final FirebaseDatabase database=FirebaseDatabase.getInstance();
                final DatabaseReference YöneticilerRef=database.getReference("Yöneticiler/"+kullanıcıText);
                final DatabaseReference MüsterilerRef=database.getReference("Müsteriler/"+kullanıcıText);

                MüsterilerRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final String kimlik2=dataSnapshot.getKey();
                        final DatabaseReference ref2=database.getReference("Müsteriler/"+kullanıcıText+"/"+parolaText);
                        ref2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                String şifre2=dataSnapshot.getKey();


                                    if((kimlik2.equals(kullanıcıText) && şifre2.equals(parolaText) && check.isChecked()!=true)){

                                    if(dataSnapshot.getValue() != null) {
                                        Intent yeniintent = new Intent(LoginActivity.this, MainActivity.class);
                                        yeniintent.putExtra("isim",kullanıcıText);
                                        yeniintent.putExtra("parola",parolaText);

                                        startActivity(yeniintent);

                                        i=i+1;
                                    }



                                    else{
                                        if (i==0){
                                        Toast.makeText(getApplicationContext(),"Hatalı Kullanıcı Numarası veya Şifre girdiniz!",Toast.LENGTH_SHORT).show();
                                    }
                                    }}
                                    else if(check.isChecked()==true){

                                        Toast.makeText(getApplicationContext(),"Yönetici Girişine izin verilmiyor!",Toast.LENGTH_SHORT).show();

                                    }
                                }



                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                YöneticilerRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                final String kimlik=dataSnapshot.getKey();
                        final DatabaseReference ref=database.getReference("Yöneticiler/"+kullanıcıText+"/"+parolaText);
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        String şifre=dataSnapshot.getKey();

                                        if (check.isChecked()==true && dataSnapshot.getValue() != null) {

                                            if (kimlik.equals(kullanıcıText) && şifre.equals(parolaText)) {


                                                Intent yeniintent = new Intent(LoginActivity.this, YoneticiActivity.class);
                                                yeniintent.putExtra("isim",kullanıcıText);
                                                yeniintent.putExtra("parola",parolaText);
                                                startActivity(yeniintent);

                                                i = i + 1;


                                            }
                                        }
                                        else{
                                        if (kimlik.equals(kullanıcıText) && şifre.equals(parolaText)) {
                                            if (dataSnapshot.getValue() != null) {
                                                Intent yeniintent = new Intent(LoginActivity.this, MainActivity.class);
                                                yeniintent.putExtra("isim",kullanıcıText);
                                                yeniintent.putExtra("parola",parolaText);
                                                startActivity(yeniintent);
                                                i=i++;
                                            }
                                            else{
                                                if(i==1){
                                                    Toast.makeText(getApplicationContext(),"Hatalı Kullanıcı Numarası veya Şifre girdiniz!",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }}
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btnKayit =findViewById(R.id.kayitBtn);
        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yeniintent2=new Intent(LoginActivity.this,KayitActivity.class);
                startActivity(yeniintent2);


            }
        });

    }
}
