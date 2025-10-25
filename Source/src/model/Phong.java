package model;

public class Phong {
    private final String maPhong;
    private final String loaiPhong;
    private final double giaGoc;

    public Phong(String maPhong, String loaiPhong, double giaGoc) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.giaGoc = giaGoc;
    }

    public String getMaPhong() { return maPhong; }
    public String getLoaiPhong() { return loaiPhong; }
    public double getGiaGoc() { return giaGoc; }

    @Override
    public String toString() {
        return maPhong + " (" + loaiPhong + ")";
    }
}