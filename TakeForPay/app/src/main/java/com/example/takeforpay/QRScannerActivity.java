package com.example.takeforpay;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {



    ZXingScannerView ScannerView;
    public Ürünler ürün;
    public double toplamfiyat=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.TableL);

    }



    @Override
    public void handleResult(final Result result) {

        SepetActivity.text.setText(result.getText());
        final String gelentext=SepetActivity.text.getText().toString();
        final String gelenpara=SepetActivity.para.getText().toString();
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference ÜrünlerRef=database.getReference("Ürünler/"+gelentext);

        ÜrünlerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for (DataSnapshot d:dataSnapshot.getChildren()) {

                   ürün = d.getValue(Ürünler.class);

                   String kod = ürün.getSericode();
                   String ad = ürün.getAdı();
                   String m = ürün.getMarka();
                   double fiyat = ürün.getFiyat();
                   double gelendoublepara=Double.parseDouble(gelenpara);

                    gelendoublepara=gelendoublepara+fiyat;

                   String stringFiyat=Double.toString(fiyat);
                   String stringtoplamFiyat=Double.toString(gelendoublepara);
                   SepetActivity.para.setText(stringtoplamFiyat);

                   // Creation row
                   TableRow tableRow = new TableRow(getApplicationContext());
                   tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));


                   // Creation textView
                   TextView serikodTXT = new TextView(getApplicationContext());
                   TextView adTXT = new TextView(getApplicationContext());
                   TextView fiyatTXT = new TextView(getApplicationContext());
                   EditText adetTXT = new EditText(getApplicationContext());

                   serikodTXT.setText(kod);
                   serikodTXT.setWidth(400);
                   serikodTXT.setHeight(140);
                   serikodTXT.setTextSize(14);
                   serikodTXT.setBackgroundColor(Color.parseColor("#00990000"));
                   serikodTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));


                   adTXT.setText(ad);
                   adTXT.setWidth(500);
                   adTXT.setHeight(140);
                   adTXT.setTextSize(14);
                   adTXT.setBackgroundColor(Color.parseColor("#00990000"));
                   adTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                   fiyatTXT.setText(stringFiyat);
                   fiyatTXT.setWidth(180);
                   fiyatTXT.setHeight(140);
                   fiyatTXT.setTextSize(14);
                   fiyatTXT.setBackgroundColor(Color.parseColor("#00990000"));
                   fiyatTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                   adetTXT.setText("1");
                   adetTXT.setWidth(100);
                   adetTXT.setHeight(150);
                   adetTXT.setTextSize(14);
                   adetTXT.setBackgroundColor(Color.parseColor("#00990000"));
                   adetTXT.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));


                   tableRow.addView(serikodTXT);
                   tableRow.addView(adTXT);
                   tableRow.addView(fiyatTXT);
                   tableRow.addView(adetTXT);




                   SepetActivity.tableLayout.addView(tableRow);

               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        onBackPressed();


    }

    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();

    }




    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();


    }

}

