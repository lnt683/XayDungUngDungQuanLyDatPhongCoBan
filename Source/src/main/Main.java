package main;

import controller.LoginController;
import service.repository.DataRepository;
import view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {            
            DataRepository.getInstance().taiDuLieu();          
            LoginFrame loginFrame = new LoginFrame();
            @SuppressWarnings("unused")
            LoginController controller = new LoginController(loginFrame);
            
            loginFrame.setVisible(true);
        });
    }
}