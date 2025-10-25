HƯỚNG DẪN SỬ DỤNG
1. Khởi động và Xác thực
Để bắt đầu, người dùng cần chạy file .jar của ứng dụng.

Đăng ký: Nếu chưa có tài khoản, người dùng nhập Tên đăng nhập và Mật khẩu mong muốn, sau đó nhấn nút "Đăng ký". Hệ thống sẽ mã hóa mật khẩu và lưu tài khoản mới.

Đăng nhập: Nếu đã có tài khoản, người dùng nhập Tên đăng nhập và Mật khẩu, sau đó nhấn nút "Đăng nhập". Hệ thống sẽ kiểm tra thông tin. Nếu thành công, giao diện chính sẽ mở ra.

<img width="733" height="430" alt="image" src="https://github.com/user-attachments/assets/be170052-0e7d-4c7b-81c8-cdecc03553ea" />

2. Giao diện Đặt phòng
Sau khi đăng nhập, màn hình chính hiển thị với Tab "Đặt phòng" được chọn sẵn.

Để thực hiện một lượt đặt phòng mới, người dùng thực hiện các bước sau:

Kiểm tra Khách hàng: Nhập số CMND/CCCD của khách và nhấn "Kiểm tra". Nếu khách đã tồn tại, tên và SĐT sẽ tự động điền. Nếu là khách mới, người dùng cần nhập thủ công.

Chọn phòng: Chọn loại phòng (Đơn, Đôi, VIP) từ danh sách thả xuống.

Chọn Loại giá: Chọn kịch bản tính giá (Ngày thường, Cuối tuần, Ngày lễ). Hệ thống sẽ tự động áp dụng đúng công thức giá (Strategy Pattern).

Chọn Số đêm: Nhập số đêm khách sẽ ở.

Xác nhận: Nhấn nút "Xác nhận Đặt". Một thông báo sẽ hiện lên xác nhận đặt phòng thành công cùng tổng chi phí.

<img width="746" height="527" alt="image" src="https://github.com/user-attachments/assets/77850a25-e165-455e-9c18-22cdbbcbf16c" />

3. Giao diện Quản lý Đặt phòng
Để xem hoặc quản lý các lượt đặt phòng, người dùng chuyển sang Tab "Quản lý Đặt phòng".

Xem danh sách: Toàn bộ các lượt đặt phòng đang hoạt động sẽ được hiển thị trên bảng.

Tự động cập nhật: Khi một lượt đặt phòng mới được tạo ở Giao diện đặt phòng, bảng dữ liệu ở Giao diện quản lý đặt sẽ tự động được làm mới ngay lập tức (nhờ Observer Pattern), trường hợp nếu dữ liệu không tải lên thì nhấn nút "Làm mới".

Trả phòng: Để trả phòng, người dùng nhấn chuột để chọn một hàng trên bảng, sau đó nhấn nút "Trả phòng". Hệ thống sẽ yêu cầu xác nhận trước khi xóa dữ liệu.

<img width="877" height="625" alt="image" src="https://github.com/user-attachments/assets/3efd584d-1a59-4b57-adf6-f6bcc44008f4" />
