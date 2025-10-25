package view;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class LoginFrame extends JFrame {
    private final JTextField txtTenDangNhap;
    private final JPasswordField txtMatKhau;
    private final JButton btnDangNhap;
    private final JButton btnDangKy;

    public LoginFrame() {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setBounds(50, 30, 100, 25);
        add(lblTenDangNhap);

        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setBounds(160, 30, 180, 25);
        add(txtTenDangNhap);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setBounds(50, 70, 100, 25);
        add(lblMatKhau);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setBounds(160, 70, 180, 25);
        add(txtMatKhau);

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setBounds(70, 120, 120, 30);
        add(btnDangNhap);

        btnDangKy = new JButton("Đăng ký");
        btnDangKy.setBounds(200, 120, 120, 30);
        add(btnDangKy);

        JLabel lblNote = new JLabel("<html>taikhoan: admin<br>matkhau: 123</html>");
        lblNote.setFont(new Font("Arial", Font.ITALIC, 12));
        lblNote.setForeground(Color.GRAY);
        lblNote.setBounds(70, 155, 250, 30);
        add(lblNote);
    }

    public JTextField getTxtTenDangNhap() {
        return txtTenDangNhap;
    }

    public JPasswordField getTxtMatKhau() {
        return txtMatKhau;
    }

    public JButton getBtnDangNhap() {
        return btnDangNhap;
    }

    public JButton getBtnDangKy() {
        return btnDangKy;
    }
}