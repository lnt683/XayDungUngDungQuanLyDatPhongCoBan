package model;

public class KhachHang {
    private final String cmnd;
    private final String ten;
    private final String sdt;

    public KhachHang(String cmnd, String ten, String sdt) {
        this.cmnd = cmnd;
        this.ten = ten;
        this.sdt = sdt;
    }

    public String getCmnd() { return cmnd; }
    public String getTen() { return ten; }
    public String getSdt() { return sdt; }
}