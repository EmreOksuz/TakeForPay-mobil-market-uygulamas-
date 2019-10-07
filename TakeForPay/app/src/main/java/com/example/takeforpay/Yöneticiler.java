package com.example.takeforpay;

public class Yöneticiler {
    private String Ykimlik;
    private String Adı;
    private String Soyadı;
    private String Yemail;
    private String Yparola;
    private  String YsirketAdi;

    public Yöneticiler(){


    }

    public Yöneticiler(String ykimlik, String yad, String ysoyad, String yemail, String yparola, String ysirketAdi) {
        Ykimlik = ykimlik;
        Adı = yad;
        Soyadı = ysoyad;
        Yemail = yemail;
        Yparola = yparola;
        YsirketAdi = ysirketAdi;
    }

    public String getYkimlik() {
        return Ykimlik;
    }

    public void setYkimlik(String ykimlik) {
        Ykimlik = ykimlik;
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

    public String getYemail() {
        return Yemail;
    }

    public void setYemail(String yemail) {
        Yemail = yemail;
    }

    public String getYparola() {
        return Yparola;
    }

    public void setYparola(String yparola) {
        Yparola = yparola;
    }

    public String getYsirketAdi() {
        return YsirketAdi;
    }

    public void setYsirketAdi(String ysirketAdi) {
        YsirketAdi = ysirketAdi;
    }
}
