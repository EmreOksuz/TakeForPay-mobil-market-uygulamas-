package com.example.takeforpay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btnCikis;
    private  Button denemebuton;
    public TextView Name;
    public ImageView imageee;
    public Müsteriler m;
    public Yöneticiler y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String gelenisim=getIntent().getStringExtra("isim");
        final String gelenp=getIntent().getStringExtra("parola");
      Name=findViewById(R.id.AdSoyadTextView);
      btnCikis =findViewById(R.id.CikisBTN);
      denemebuton=findViewById(R.id.AyarlarBTN);
      imageee=findViewById(R.id.imageVİEW);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference YöneticilerRef=database.getReference("Yöneticiler/"+gelenisim+"/"+gelenp);
        final DatabaseReference MüsterilerRef=database.getReference("Müsteriler/"+gelenisim+"/"+gelenp);

      YöneticilerRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              for (DataSnapshot d:dataSnapshot.getChildren()) {

                  y = d.getValue(Yöneticiler.class);
                    String k=dataSnapshot.getKey();
                  String kod = y.getAdı();
                  String kod2 = y.getSoyadı();
                  String email = y.getYemail();
                  Name.setText(kod+" "+kod2);
                  MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                  try {
                      BitMatrix bitMatrix=multiFormatWriter.encode(k, BarcodeFormat.QR_CODE,300,300);
                      BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                      Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                      imageee.setImageBitmap(bitmap);
                  } catch (WriterException e) {
                      e.printStackTrace();
                  }
              }

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
        MüsterilerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    m = d.getValue(Müsteriler.class);
                    String kod = m.getAdı();
                    String kod2 = m.getSoyadı();
                    String email = m.getMemail();
                    String k=dataSnapshot.getKey();
                    Name.setText(kod + " " + kod2);


                    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix=multiFormatWriter.encode(k, BarcodeFormat.QR_CODE,300,300);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        imageee.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        btnCikis.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent yeniintent=new Intent(MainActivity.this,LoginActivity.class);
              startActivity(yeniintent);

          }
      });

        denemebuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yeniintent2=new Intent(MainActivity.this,SepetActivity.class);
                startActivity(yeniintent2);

            }
        });


    }
}
