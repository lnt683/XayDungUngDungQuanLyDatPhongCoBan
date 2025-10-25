package controller;

import service.repository.DataRepository;
import view.QuanLyDatPhongPanel;

import javax.swing.*;

public class QuanLyController {

    private final QuanLyDatPhongPanel view;
    private final DataRepository repository;

    public QuanLyController(QuanLyDatPhongPanel view) {
        this.view = view;
        this.repository = DataRepository.getInstance(); 
        initController();
    }
    
    @SuppressWarnings("unused")
    private void initController() {
        view.getBtnLamMoi().addActionListener(e -> lamMoiDuLieu());
        
        view.getBtnTraPhong().addActionListener(e -> traPhong());
    }

    private void lamMoiDuLieu() {
        view.loadDataToTable();
    }

    private void traPhong() {
        int selectedRow = view.getTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, 
                    "Vui lòng chọn một lượt đặt phòng để trả.", 
                    "Chưa chọn", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view,
                "Bạn có chắc chắn muốn trả phòng này (hành động này sẽ xóa dữ liệu)?",
                "Xác nhận trả phòng",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int modelRow = view.getTable().convertRowIndexToModel(selectedRow);
            String maDatPhong = (String) view.getTableModel().getValueAt(modelRow, 0);
            repository.xoaDatPhong(maDatPhong);
            JOptionPane.showMessageDialog(view, 
                    "Đã trả phòng (xóa) thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            lamMoiDuLieu();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Lỗi khi trả phòng: " + ex.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}