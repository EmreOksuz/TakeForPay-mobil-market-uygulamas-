package com.example.takeforpay;

import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class KayitActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager viewpager;
    public Button Mkaydi;
    public Button Ykaydi;
    public EditText MTC;
    public EditText MAD;
    public EditText MSOYAD;
    public EditText MEMAİL;
    public EditText MPAROLA;
    public EditText YTC;
    public EditText YAD;
    public EditText YSOYAD;
    public EditText YEMAİL;
    public EditText YPAROLA;
    public EditText YSİRKETADİ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        tab=(TabLayout) findViewById(R.id.tabLayout);
        viewpager=(ViewPager)findViewById(R.id.viewpager);


        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());

        adapter.fragmentEkle(new musteri_fragment(),"Müşteri Kaydı");
        adapter.fragmentEkle(new yonetici_fragment(),"Yönetici Kaydı");
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);


    }
    public void MusteriKaydet(View v){

        MTC=(EditText) findViewById(R.id.msteriTcText);
        MAD=(EditText)findViewById(R.id.msteriAdText);
        MSOYAD=(EditText)findViewById(R.id.msteriSoyadText);
        MEMAİL=(EditText)findViewById(R.id.msteriEmailText);
        MPAROLA=(EditText)findViewById(R.id.msteriSifreText);
        Mkaydi=(Button)findViewById(R.id.KaydetBTN);


        Mkaydi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mtc = MTC.getText().toString();
                String mad=MAD.getText().toString();
                String msoyad=MSOYAD.getText().toString();
                String memail=MEMAİL.getText().toString();
                String mparola=MPAROLA.getText().toString();

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference MüsterilerRef=database.getReference("Müsteriler/"+mtc+"/"+mparola+"/"+mad+" "+msoyad);
                Müsteriler müsteri=new Müsteriler(mtc,mad,msoyad,memail,mparola);



                MüsterilerRef.child("Tc Kimlik").setValue(mtc);
                MüsterilerRef.child("Adı").setValue(mad);
                MüsterilerRef.child("Soyadı").setValue(msoyad);
                MüsterilerRef.child("E-mail").setValue(memail);
                MüsterilerRef.child("Şifre").setValue(mparola);



            }
        });

    }

    public void YöneticiKaydet(View v){
        YTC=(EditText) findViewById(R.id.YntcTcText);
        YAD=(EditText)findViewById(R.id.YntcAdText);
        YSOYAD=(EditText)findViewById(R.id.YntcSoyadText);
        YEMAİL=(EditText)findViewById(R.id.YntcEmailText);
        YPAROLA=(EditText)findViewById(R.id.YntcSifreText);
        YSİRKETADİ=(EditText)findViewById(R.id.YntcSirketText);
        Ykaydi=(Button)findViewById(R.id.kaydetYBTN);

        Ykaydi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ytc = YTC.getText().toString();
                String yad=YAD.getText().toString();
                String ysoyad=YSOYAD.getText().toString();
                String yemail=YEMAİL.getText().toString();
                String yparola=YPAROLA.getText().toString();
                String ysirket=YSİRKETADİ.getText().toString();

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference YöneticilerRef=database.getReference("Yöneticiler/"+ytc+"/"+yparola+"/"+yad+" "+ysoyad);
                Yöneticiler Yönetici=new Yöneticiler(ytc,yad,ysoyad,yemail,yparola,ysirket);

                YöneticilerRef.child("Tc Kimlik").setValue(ytc);
                YöneticilerRef.child("Adı").setValue(yad);
                YöneticilerRef.child("Soyadı").setValue(ysoyad);
                YöneticilerRef.child("E-mail").setValue(yemail);
                YöneticilerRef.child("Şifre").setValue(yparola);
                YöneticilerRef.child("Şirket Adı").setValue(ysirket);




            }
        });


    }
    class MyAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragListe=new ArrayList<>();
        private List<String> fragBaslikListe=new ArrayList<String>();



        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragListe.get(position);
        }

        @Override
        public int getCount() {
            return fragListe.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return (CharSequence) fragBaslikListe.get(position);
        }
        public void fragmentEkle(Fragment fragment,String baslik){
            fragListe.add(fragment);
            fragBaslikListe.add(baslik);



        }


    }


}
