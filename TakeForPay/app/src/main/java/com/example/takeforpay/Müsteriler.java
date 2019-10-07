package com.example.takeforpay;

import android.widget.EditText;

public class Müsteriler {
    private String Mkimlik;
    private String Adı;
    private String Soyadı;
    private String Memail;
    private String Mparola;

    public Müsteriler(EditText MTC, EditText MAD, EditText MSOYAD, EditText MEMAİL, EditText MPAROLA){

    }
    public Müsteriler() {

    }
    public Müsteriler(String mkimlik, String mad, String msoyad, String memail, String mparola) {
        Mkimlik = mkimlik;
        Adı = mad;
        Soyadı = msoyad;
        Memail = memail;
        Mparola = mparola;
    }

    public String getMkimlik() {
        return Mkimlik;
    }

    public void setMkimlik(String mkimlik) {
        Mkimlik = mkimlik;
    }

    public String getAdı() {
        return Adı;
    }

    public void setAdı(String adı) {
        Adı = adı;
    }

    public String getSoyadı() {
        return Soyadı;
    }

    public void setSoyadı(String soyadı) {
        Soyadı = soyadı;
    }

    public String getMemail() {
        return Memail;
    }

    public void setMemail(String memail) {
        Memail = memail;
    }

    public String getMparola() {
        return Mparola;
    }

    public void setMparola(String mparola) {
        Mparola = mparola;
    }
}
