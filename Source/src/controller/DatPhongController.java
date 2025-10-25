package controller;

import model.DatPhong;
import model.KhachHang;
import model.Phong;
import service.giaphong.GiaPhongStrategyFactory;
import service.giaphong.IGiaPhongStrategy;
import service.phong.IPhongGia;
import service.phong.PhongGiaCoBan;
import service.repository.DataRepository;
import view.DatPhongPanel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DatPhongController {

    private final DatPhongPanel view;
    private final DataRepository repository;

    public DatPhongController(DatPhongPanel view) {
        this.view = view;
        this.repository = DataRepository.getInstance();

        loadPhongData();
        initController();
    }

    @SuppressWarnings("unused")
    private void initController() {
        view.getBtnKiemTraKhach().addActionListener(e -> kiemTraKhach());
        view.getBtnXacNhanDat().addActionListener(e -> xacNhanDatPhong());
    }

    private void loadPhongData() {
        List<Phong> dsPhong = repository.getDsPhong();
        for (Phong p : dsPhong) {
            view.getCbChonPhong().addItem(p);
        }
    }

    private void kiemTraKhach() {
        String cmnd = view.getTxtCmnd().getText();
        if (cmnd.isBlank()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập CMND.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        repository.findKhachHangByCmnd(cmnd)
                .ifPresentOrElse(
                        kh -> {
                            view.getTxtTenKhach().setText(kh.getTen());
                            view.getTxtSdt().setText(kh.getSdt());
                            view.getTxtTenKhach().setEditable(false);
                            view.getTxtSdt().setEditable(false);
                        },
                        () -> {
                            JOptionPane.showMessageDialog(view, "Không tìm thấy khách hàng. Vui lòng nhập thông tin khách mới.", "Khách hàng mới", JOptionPane.INFORMATION_MESSAGE);
                            view.getTxtTenKhach().setText("");
                            view.getTxtSdt().setText("");
                            view.getTxtTenKhach().setEditable(true);
                            view.getTxtSdt().setEditable(true);
                        }
                );
    }

    private void xacNhanDatPhong() {
        try {
            String cmnd = view.getTxtCmnd().getText();
            String ten = view.getTxtTenKhach().getText();
            String sdt = view.getTxtSdt().getText();
            Phong phong = (Phong) view.getCbChonPhong().getSelectedItem();
            int soDem = (int) view.getSpinSoDem().getValue();
            String loaiGia = (String) view.getCbLoaiGia().getSelectedItem();

            if (cmnd.isBlank() || ten.isBlank() || sdt.isBlank() || phong == null) {
                JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            KhachHang khachHang = repository.findKhachHangByCmnd(cmnd)
                    .orElse(new KhachHang(cmnd, ten, sdt));
            repository.themKhachHang(khachHang);

            IGiaPhongStrategy strategy = GiaPhongStrategyFactory.createStrategy(loaiGia);

            IPhongGia phongGia = new PhongGiaCoBan(phong, strategy, soDem);
            double tongTien = phongGia.getGia();
            String moTa = phongGia.getMoTa();
            String maDatPhong = "DP" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
            DatPhong datPhongMoi = new DatPhong(maDatPhong, khachHang, phong, LocalDate.now(), soDem, tongTien);

            repository.themDatPhong(datPhongMoi);

            JOptionPane.showMessageDialog(view,
                    String.format("Đặt phòng thành công!\nKhách hàng: %s\n%s\nTổng tiền: %,.0f VNĐ",
                            ten, moTa, tongTien),
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
            
            resetForm();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        view.getTxtCmnd().setText("");
        view.getTxtTenKhach().setText("");
        view.getTxtSdt().setText("");
        view.getTxtTenKhach().setEditable(true);
        view.getTxtSdt().setEditable(true);
        view.getCbChonPhong().setSelectedIndex(0);
        view.getCbLoaiGia().setSelectedIndex(0);
        view.getSpinSoDem().setValue(1);
    }
}