package com.example.myposyandu.model;

import java.util.List;

public class ModelDataBayi {
    private int id_bayi;
    private String nama_bayi,tgl_lahir, jenis_kelamin;

    public ModelDataBayi(int id_bayi, String nama_bayi, String tgl_lahir, String jenis_kelamin) {
        this.id_bayi = id_bayi;
        this.nama_bayi = nama_bayi;
        this.tgl_lahir = tgl_lahir;
        this.jenis_kelamin = jenis_kelamin;
    }

    public int getId_bayi() {
        return id_bayi;
    }

    public void setId_bayi(int id_bayi) {
        this.id_bayi = id_bayi;
    }

    public String getNama_bayi() {
        return nama_bayi;
    }

    public void setNama_bayi(String nama_bayi) {
        this.nama_bayi = nama_bayi;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }
}
