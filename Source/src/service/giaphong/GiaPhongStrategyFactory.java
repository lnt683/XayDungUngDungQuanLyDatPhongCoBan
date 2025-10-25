package service.giaphong;

public class GiaPhongStrategyFactory {
    public static IGiaPhongStrategy createStrategy(String loaiGia) {
        if (loaiGia == null) {
            return new GiaNgayThuongStrategy();
        }
        
        switch (loaiGia) {
            case "Cuối tuần":
                return new GiaCuoiTuanStrategy();
            case "Ngày lễ":
                return new GiaNgayLeStrategy();
            case "Ngày thường":
            default:
                return new GiaNgayThuongStrategy();
        }
    }
}