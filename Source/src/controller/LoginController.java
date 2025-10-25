package controller;

import service.repository.DataRepository;
import view.LoginFrame;
import view.MainFrame;
import javax.swing.*;

public class LoginController {
    private final LoginFrame loginFrame;
    private final DataRepository dataRepository;

    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        this.dataRepository = DataRepository.getInstance();

        initListeners();
    }

    @SuppressWarnings("unused")
    private void initListeners() {
        loginFrame.getBtnDangNhap().addActionListener(e -> onDangNhap());
        loginFrame.getBtnDangKy().addActionListener(e -> onDangKy());
    }

    private void onDangKy() {
        String username = loginFrame.getTxtTenDangNhap().getText();
        String password = new String(loginFrame.getTxtMatKhau().getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Vui lòng nhập đủ tên và mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean thanhCong = dataRepository.dangKy(username, password);

        if (thanhCong) {
            JOptionPane.showMessageDialog(loginFrame, "Đăng ký thành công! Vui lòng đăng nhập.");
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Tên đăng nhập đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onDangNhap() {
        String username = loginFrame.getTxtTenDangNhap().getText();
        String password = new String(loginFrame.getTxtMatKhau().getPassword());

        boolean thanhCong = dataRepository.dangNhap(username, password);

        if (thanhCong) {
            JOptionPane.showMessageDialog(loginFrame, "Đăng nhập thành công!");            
            loginFrame.dispose();
            MainFrame mainFrame = new MainFrame();

            @SuppressWarnings("unused")
            DatPhongController datPhongController = new DatPhongController(mainFrame.getDatPhongPanel());

            @SuppressWarnings("unused")
            QuanLyController quanLyController = new QuanLyController(mainFrame.getQuanLyPanel());

            mainFrame.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}