package service.phong;

import model.Phong;
import service.giaphong.IGiaPhongStrategy;

public class PhongGiaCoBan implements IPhongGia {

    protected Phong phong;
    protected IGiaPhongStrategy strategy;
    protected int soDem;

    public PhongGiaCoBan(Phong phong, IGiaPhongStrategy strategy, int soDem) {
        this.phong = phong;
        this.strategy = strategy;
        this.soDem = soDem;
    }

    @Override
    public double getGia() {
        return strategy.tinhGia(phong, soDem);
    }

    @Override
    public String getMoTa() {
        return phong.getLoaiPhong() + " (" + soDem + " đêm)";
    }
}