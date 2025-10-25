package service.repository;

import model.DatPhong;
import model.KhachHang;
import model.Phong;
import model.TaiKhoan; 

import java.io.*;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64; 
import java.util.List;
import java.util.Optional;

public class DataRepository {
    private static DataRepository instance;

    private final List<RepositoryObserver> observers = new ArrayList<>();

    private final List<Phong> dsPhong = new ArrayList<>();
    private final List<KhachHang> dsKhachHang = new ArrayList<>();
    private final List<DatPhong> dsDatPhong = new ArrayList<>();
    private final List<TaiKhoan> dsTaiKhoan = new ArrayList<>(); 

    private static final String PHONG_FILE = "Database/phong.txt";
    private static final String KHACHHANG_FILE = "Database/khachhang.txt";
    private static final String DATPHONG_FILE = "Database/datphong.txt";
    private static final String TAIKHOAN_FILE = "Database/taikhoan.txt"; 

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public void addObserver(RepositoryObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    private void notifyObservers() {
        for (RepositoryObserver observer : observers) {
            observer.update();
        }
    }

    public List<Phong> getDsPhong() {
        return dsPhong;
    }

    public List<DatPhong> getDsDatPhong() {
        return dsDatPhong;
    }

    public Optional<KhachHang> findKhachHangByCmnd(String cmnd) {
        return dsKhachHang.stream().filter(kh -> kh.getCmnd().equals(cmnd)).findFirst();
    }

    public Optional<Phong> findPhongByMa(String maPhong) {
        return dsPhong.stream().filter(p -> p.getMaPhong().equals(maPhong)).findFirst();
    }

    public void themKhachHang(KhachHang kh) {
        if (findKhachHangByCmnd(kh.getCmnd()).isEmpty()) {
            dsKhachHang.add(kh);
            luuDsKhachHang();
        }
    }

    public void themDatPhong(DatPhong dp) {
        dsDatPhong.add(dp);
        luuDsDatPhong();
        notifyObservers();
    }

    public void xoaDatPhong(String maDatPhong) {
        boolean removed = dsDatPhong.removeIf(dp -> dp.getMaDatPhong().equals(maDatPhong));
        
        if (removed) {
            luuDsDatPhong();
            notifyObservers();
        }
    }

    public void taiDuLieu() {
        taiDsPhong();
        taiDsKhachHang();
        taiDsDatPhong();
        taiDsTaiKhoan();
    }

    private void taiDsPhong() {
        dsPhong.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(PHONG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    dsPhong.add(new Phong(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file phòng: " + e.getMessage());
        }
    }

    private void taiDsKhachHang() {
        dsKhachHang.clear();
        File file = new File(KHACHHANG_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    dsKhachHang.add(new KhachHang(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file khách hàng: " + e.getMessage());
        }
    }

    private void taiDsDatPhong() {
        dsDatPhong.clear();
        File file = new File(DATPHONG_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    KhachHang kh = findKhachHangByCmnd(parts[1]).orElse(null);
                    Phong p = findPhongByMa(parts[2]).orElse(null);
                    if (kh != null && p != null) {
                        dsDatPhong.add(new DatPhong(
                                parts[0],
                                kh,
                                p,
                                LocalDate.parse(parts[3]),
                                Integer.parseInt(parts[4]),
                                Double.parseDouble(parts[5])
                        ));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file đặt phòng: " + e.getMessage());
        }
    }

    private void luuDsKhachHang() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(KHACHHANG_FILE, false))) {
            for (KhachHang kh : dsKhachHang) {
                bw.write(String.format("%s;%s;%s\n", kh.getCmnd(), kh.getTen(), kh.getSdt()));
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file khách hàng: " + e.getMessage());
        }
    }

    private void luuDsDatPhong() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATPHONG_FILE, false))) {
            for (DatPhong dp : dsDatPhong) {
                bw.write(String.format("%s;%s;%s;%s;%d;%f\n",
                        dp.getMaDatPhong(),
                        dp.getKhachHang().getCmnd(),
                        dp.getPhong().getMaPhong(),
                        dp.getNgayDat().toString(),
                        dp.getSoDem(),
                        dp.getTongTien()
                ));
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file đặt phòng: " + e.getMessage());
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Không tìm thấy thuật toán mã hóa: " + e.getMessage());
            return password; 
        }
    }

    private void taiDsTaiKhoan() {
        dsTaiKhoan.clear();
        File file = new File(TAIKHOAN_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Lỗi tạo file tài khoản: " + e.getMessage());
                return;
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    dsTaiKhoan.add(new TaiKhoan(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file tài khoản: " + e.getMessage());
        }
    }

    private void luuDsTaiKhoan() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TAIKHOAN_FILE, false))) {
            for (TaiKhoan tk : dsTaiKhoan) {
                bw.write(String.format("%s;%s\n", tk.getTenDangNhap(), tk.getMatKhau()));
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file tài khoản: " + e.getMessage());
        }
    }

    public boolean dangKy(String username, String password) {
        boolean daTonTai = dsTaiKhoan.stream()
                .anyMatch(tk -> tk.getTenDangNhap().equals(username));
        if (daTonTai) {
            return false;
        }
        String hashedPass = hashPassword(password);
        dsTaiKhoan.add(new TaiKhoan(username, hashedPass));
        luuDsTaiKhoan();
        return true;
    }

    public boolean dangNhap(String username, String password) {
        String hashedPass = hashPassword(password);
        Optional<TaiKhoan> tkOpt = dsTaiKhoan.stream()
                .filter(tk -> tk.getTenDangNhap().equals(username))
                .findFirst();
        if (tkOpt.isPresent()) {
            return tkOpt.get().getMatKhau().equals(hashedPass);
        }
        return false;
    }
}