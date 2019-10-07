package com.example.takeforpay;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StokKontrolActivity extends AppCompatActivity {


    public Ürünler ürünler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok_kontrol);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayout);


        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference ÜrünlerRef=database.getReference("Ürünler");
        ÜrünlerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d:dataSnapshot.getChildren()) {

                    String key=d.getKey();
                    final FirebaseDatabase database=FirebaseDatabase.getInstance();
                    final DatabaseReference ÜrünicerikRef=database.getReference("Ürünler/"+key);

                    ÜrünicerikRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot e:dataSnapshot.getChildren()) {
                                ürünler =e.getValue(Ürünler.class);
                                String kod = ürünler.getSericode();
                                String ad = ürünler.getAdı();
                                int miktar = ürünler.getMiktar();
                                int stok = ürünler.getStok();


                                // Creation row
                                TableRow tableRow = new TableRow(getApplicationContext());
                                tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));


                                // Creation textView
                                TextView serikodTXT = new TextView(getApplicationContext());
                                TextView adTXT = new TextView(getApplicationContext());
                                TextView gramajTxt = new TextView(getApplicationContext());
                                TextView adetTXT = new TextView(getApplicationContext());

                                serikodTXT.setText("   "+kod);
                                serikodTXT.setWidth(400);
                                serikodTXT.setHeight(140);
                                serikodTXT.setTextSize(14);
                                serikodTXT.setBackgroundColor(Color.parseColor("#00990000"));
                                serikodTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));


                                adTXT.setText("   "+ad);
                                adTXT.setWidth(500);
                                adTXT.setHeight(140);
                                adTXT.setTextSize(14);
                                adTXT.setBackgroundColor(Color.parseColor("#00990000"));
                                adTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                gramajTxt.setText("   "+miktar);
                                gramajTxt.setWidth(180);
                                gramajTxt.setHeight(140);
                                gramajTxt.setTextSize(14);
                                gramajTxt.setBackgroundColor(Color.parseColor("#00990000"));
                                gramajTxt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                adetTXT.setText("    "+stok);
                                adetTXT.setWidth(100);
                                adetTXT.setHeight(150);
                                adetTXT.setTextSize(14);
                                adetTXT.setBackgroundColor(Color.parseColor("#00990000"));
                                adetTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));


                                tableRow.addView(serikodTXT);
                                tableRow.addView(adTXT);
                                tableRow.addView(gramajTxt);
                                tableRow.addView(adetTXT);


                                tableLayout.addView(tableRow);


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
