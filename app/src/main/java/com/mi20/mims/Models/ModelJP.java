package com.mi20.mims.Models;

public class ModelJP {
    String hari, matkul, pengampu, waktu, ruang, id;

    public ModelJP(String hari, String matkul, String pengampu, String waktu, String ruang, String id) {
        this.hari = hari;
        this.matkul = matkul;
        this.pengampu = pengampu;
        this.waktu = waktu;
        this.ruang = ruang;
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getPengampu() {
        return pengampu;
    }

    public void setPengampu(String pengampu) {
        this.pengampu = pengampu;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
