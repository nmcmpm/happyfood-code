package com.example.duan;

public class Food {
    private String tenNH;
    private String tenMon;
    private String diaChi;
    private String noiDung;
    private boolean like;
    private int stt;

    public Food(String tenNH, String tenMon, String diaChi, String noiDung, boolean like, int stt) {
        this.tenNH = tenNH;
        this.tenMon = tenMon;
        this.diaChi = diaChi;
        this.noiDung = noiDung;
        this.like = like;
        this.stt = stt;
    }
    
    public Food(String owner, String food, String description, boolean b, String id) {

    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    
    public String getTenNH() {
        return tenNH;
    }

    public void setTenNH(String tenNH) {
        this.tenNH = tenNH;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Food{" +
                "tenNH='" + tenNH + '\'' +
                ", tenMon='" + tenMon + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", like=" + like +
                ", stt=" + stt +
                '}';
    }
}
