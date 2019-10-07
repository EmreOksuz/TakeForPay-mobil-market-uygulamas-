package com.example.takeforpay;

public class Ürünler {
    private  String Sericode;
    private  String Adı;
    private  String Marka;
    private  int Miktar;
    private  int Stok;
    private  double Fiyat;

    public Ürünler(){
    }

    public Ürünler(String sericode, String adı, String marka, int miktar, int stok, double fiyat) {
        Sericode = sericode;
        Adı = adı;
        Marka = marka;
        Miktar = miktar;
        Stok = stok;
        Fiyat = fiyat;
    }

    public String getSericode() {
        return Sericode;
    }

    public void setSericode(String sericode) {
        Sericode = sericode;
    }

    public String getAdı() {
        return Adı;
    }

    public void setAdı(String adı) {
        Adı = adı;
    }

    public String getMarka() {
        return Marka;
    }

    public void setMarka(String marka) {
        Marka = marka;
    }

    public int getMiktar() {
        return Miktar;
    }

    public void setMiktar(int miktar) {
        Miktar = miktar;
    }

    public int getStok() {
        return Stok;
    }

    public void setStok(int stok) {
        Stok = stok;
    }

    public double getFiyat() {
        return Fiyat;
    }

    public void setFiyat(double fiyat) {
        Fiyat = fiyat;
    }
}
