package service.giaphong;

import model.Phong;

public class GiaNgayThuongStrategy implements IGiaPhongStrategy {
    @Override
    public double tinhGia(Phong phong, int soDem) {
        return phong.getGiaGoc() * soDem;
    }
}