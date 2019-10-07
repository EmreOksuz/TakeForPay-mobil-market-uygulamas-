package com.example.takeforpay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SepetActivity extends AppCompatActivity {

    public static TableLayout tableLayout;
    Button scanner;
    Button ödeme;
    public static TextView text;
    public static TextView para;
    public TableLayout tablo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepet);

        scanner=(Button)findViewById(R.id.ALBTN);
        ödeme=(Button)findViewById(R.id.ÖdeBTN);
        text=(TextView)findViewById(R.id.QRTXT);
        para=(TextView)findViewById(R.id.ToplamTutarTXT);
        tableLayout = (TableLayout) findViewById(R.id.TableL);




        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(getApplicationContext(),QRScannerActivity.class));




            }
        });


        ödeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OdemeActivity.class));
            }
        });

    }
}
