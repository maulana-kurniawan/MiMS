package com.mi20.mims.Models;

public class ModelDosen {
    private String nid, nama_dosen;

    public ModelDosen(String nid, String nama_dosen) {
        this.nid = nid;
        this.nama_dosen = nama_dosen;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }
}
