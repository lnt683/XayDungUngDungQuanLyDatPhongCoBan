package view;

import model.DatPhong;
import service.repository.DataRepository;
import service.repository.RepositoryObserver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class QuanLyDatPhongPanel extends JPanel implements RepositoryObserver {

    private final JTable table;
    private final DefaultTableModel tableModel;
    private JButton btnLamMoi;
    private JButton btnTraPhong;

    public QuanLyDatPhongPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnLamMoi = new JButton("Làm mới danh sách");
        btnTraPhong = new JButton("Trả phòng");

        topPanel.add(btnLamMoi);
        topPanel.add(btnTraPhong);

        String[] columnNames = {"Mã ĐP", "Khách Hàng", "CMND", "Phòng", "Ngày Đặt", "Số Đêm", "Tổng Tiền"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadDataToTable();
    }

    public final void loadDataToTable() {
        tableModel.setRowCount(0);
        List<DatPhong> dsDatPhong = DataRepository.getInstance().getDsDatPhong();
        for (DatPhong dp : dsDatPhong) {
            Object[] row = {
                    dp.getMaDatPhong(),
                    dp.getKhachHang().getTen(),
                    dp.getKhachHang().getCmnd(),
                    dp.getPhong().getMaPhong(),
                    dp.getNgayDat().toString(),
                    dp.getSoDem(),
                    String.format("%,.0f VNĐ", dp.getTongTien())
            };
            tableModel.addRow(row);
        }
    }

    public JButton getBtnLamMoi() {
        return btnLamMoi;
    }

    public JButton getBtnTraPhong() {
        return btnTraPhong;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    @Override
    public void update() {
        System.out.println("Observer: Đã phát hiện thay đổi dữ liệu! Tải lại bảng...");
        loadDataToTable();
    }
}