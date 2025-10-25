package view;

import service.repository.DataRepository;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private final JTabbedPane tabbedPane;
    private final DatPhongPanel datPhongPanel;
    private final QuanLyDatPhongPanel quanLyPanel;

    public MainFrame() {
        setTitle("Hệ Thống Quản Lý Đặt Phòng");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        datPhongPanel = new DatPhongPanel();
        quanLyPanel = new QuanLyDatPhongPanel();

        DataRepository.getInstance().addObserver(quanLyPanel);

        tabbedPane.addTab("Đặt Phòng", datPhongPanel);
        tabbedPane.addTab("Quản Lý", quanLyPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    public DatPhongPanel getDatPhongPanel() {
        return datPhongPanel;
    }

    public QuanLyDatPhongPanel getQuanLyPanel() {
        return quanLyPanel;
    }
}