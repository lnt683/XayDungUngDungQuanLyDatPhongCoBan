package service.phong;

public abstract class PhongGiaDecorator implements IPhongGia {
    
    protected IPhongGia wrappee;

    public PhongGiaDecorator(IPhongGia source) {
        this.wrappee = source;
    }

    @Override
    public double getGia() {
        return wrappee.getGia();
    }

    @Override
    public String getMoTa() {
        return wrappee.getMoTa();
    }
}