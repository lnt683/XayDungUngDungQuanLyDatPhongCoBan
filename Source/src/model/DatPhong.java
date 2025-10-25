package model;

import java.time.LocalDate;

public class DatPhong {
    private final String maDatPhong;
    private final KhachHang khachHang;
    private final Phong phong;
    private final LocalDate ngayDat;
    private final int soDem;
    private final double tongTien;

    public DatPhong(String maDatPhong, KhachHang khachHang, Phong phong, LocalDate ngayDat, int soDem, double tongTien) {
        this.maDatPhong = maDatPhong;
        this.khachHang = khachHang;
        this.phong = phong;
        this.ngayDat = ngayDat;
        this.soDem = soDem;
        this.tongTien = tongTien;
    }

    public String getMaDatPhong() { return maDatPhong; }
    public KhachHang getKhachHang() { return khachHang; }
    public Phong getPhong() { return phong; }
    public LocalDate getNgayDat() { return ngayDat; }
    public int getSoDem() { return soDem; }
    public double getTongTien() { return tongTien; }
}