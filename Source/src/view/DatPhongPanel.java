package view;

import model.Phong;

import javax.swing.*;
import java.awt.*;

public class DatPhongPanel extends JPanel {
    private final JTextField txtCmnd, txtTenKhach, txtSdt;
    private final JButton btnKiemTraKhach, btnXacNhanDat;
    private final JComboBox<Phong> cbChonPhong;
    private final JSpinner spinSoDem;
    private final JComboBox<String> cbLoaiGia;

    public DatPhongPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel khachPanel = new JPanel(new GridBagLayout());
        khachPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        khachPanel.add(new JLabel("CMND:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtCmnd = new JTextField(20);
        khachPanel.add(txtCmnd, gbc);

        gbc.gridx = 3; gbc.gridwidth = 1;
        btnKiemTraKhach = new JButton("Kiểm tra");
        khachPanel.add(btnKiemTraKhach, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        khachPanel.add(new JLabel("Tên khách:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtTenKhach = new JTextField(20);
        khachPanel.add(txtTenKhach, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        khachPanel.add(new JLabel("SĐT:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtSdt = new JTextField(20);
        khachPanel.add(txtSdt, gbc);

        JPanel datPhongInfoPanel = new JPanel(new GridBagLayout());
        datPhongInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đặt phòng"));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        datPhongInfoPanel.add(new JLabel("Chọn phòng:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        cbChonPhong = new JComboBox<>();
        datPhongInfoPanel.add(cbChonPhong, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        datPhongInfoPanel.add(new JLabel("Số đêm:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        spinSoDem = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        datPhongInfoPanel.add(spinSoDem, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        datPhongInfoPanel.add(new JLabel("Loại giá:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        cbLoaiGia = new JComboBox<>(new String[]{"Ngày thường", "Cuối tuần", "Ngày lễ"});
        datPhongInfoPanel.add(cbLoaiGia, gbc);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(khachPanel, BorderLayout.NORTH);
        centerPanel.add(datPhongInfoPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnXacNhanDat = new JButton("Xác nhận Đặt phòng");
        southPanel.add(btnXacNhanDat);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }
    public JTextField getTxtCmnd() { return txtCmnd; }
    public JTextField getTxtTenKhach() { return txtTenKhach; }
    public JTextField getTxtSdt() { return txtSdt; }
    public JButton getBtnKiemTraKhach() { return btnKiemTraKhach; }
    public JButton getBtnXacNhanDat() { return btnXacNhanDat; }
    public JComboBox<Phong> getCbChonPhong() { return cbChonPhong; }
    public JSpinner getSpinSoDem() { return spinSoDem; }
    public JComboBox<String> getCbLoaiGia() { return cbLoaiGia; }
}