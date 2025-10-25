package service.giaphong;

import model.Phong;

public class GiaNgayLeStrategy implements IGiaPhongStrategy {
    @Override
    public double tinhGia(Phong phong, int soDem) {
        return phong.getGiaGoc() * 1.5 * soDem;
    }
}