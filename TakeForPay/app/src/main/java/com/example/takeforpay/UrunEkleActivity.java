package com.example.takeforpay;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UrunEkleActivity extends AppCompatActivity {

    public EditText Serikod;
    public EditText ürünad;
    public EditText ürünmiktarı;
    public EditText ürünstok;
    public EditText ürünfiyat;
    public EditText ürünmarka;
    public Button Ekle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        Serikod=findViewById(R.id.SerikodTXT);
        ürünad=findViewById(R.id.ürünadTXT);
        ürünmiktarı=findViewById(R.id.ÜrünGramajTXT);
        ürünstok=findViewById(R.id.ÜrünadetTXT);
        ürünfiyat=findViewById(R.id.ÜrünFiyatTXT);
        ürünmarka=findViewById(R.id.ürünMarkaTXT);
        Ekle=findViewById(R.id.ÜrünekleBTN);

        Ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    final    String sk = Serikod.getText().toString();
                    final    String üa = ürünad.getText().toString();
                    final    String ümi = ürünmiktarı.getText().toString();
                    final    int valÜmi = Integer.parseInt(ümi);
                    final    String üs = ürünstok.getText().toString();
                    final   int valÜS = Integer.parseInt(üs);
                    final   String üf = ürünfiyat.getText().toString();
                    final   double valÜF = Double.parseDouble(üf);
                    final   String üm = ürünmarka.getText().toString();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference SerikodRef = database.getReference("Ürünler/" +sk);
                    final DatabaseReference ÜrünlerRef = database.getReference("Ürünler/" +sk+"/"+valÜS);


                    SerikodRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            String SerikodValue=SerikodRef.getKey();

                            if (sk.equals(SerikodValue)){

                                ÜrünlerRef.child("Sericode").setValue(sk);
                                ÜrünlerRef.child("Adı").setValue(üa);
                                ÜrünlerRef.child("Stok").setValue(valÜS);
                                ÜrünlerRef.child("Miktar").setValue(valÜmi);
                                ÜrünlerRef.child("Fiyat").setValue(valÜF );
                                ÜrünlerRef.child("Marka").setValue(üm);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Yanlış biçimde ürün girdiniz!",Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
