package service.giaphong;

import model.Phong;

@FunctionalInterface
public interface IGiaPhongStrategy {
    double tinhGia(Phong phong, int soDem);
}