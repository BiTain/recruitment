USE tuyendung_tts;

INSERT INTO TAIKHOAN (maTaiKhoan, email, matKhau, vaiTro, trangThai)
VALUES ('TK026', 'bao@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG');
-- Thêm dữ liệu vào bảng TAIKHOAN
INSERT INTO TAIKHOAN (maTaiKhoan, email, matKhau, vaiTro, trangThai)
VALUES ('TK001', 'admin@admin.com', '123456', 'ADMIN', 'HOAT_DONG'),
       ('TK002', 'dhbachkhoa@gmail.com', '123456', 'NHA_TRUONG', 'HOAT_DONG'),
       ('TK003', 'dhkinhtedanang@gmail.com', '123456', 'NHA_TRUONG', 'KHONG_HOAT_DONG'),
       ('TK004', 'dhngonngu@gmail.com', '123456', 'NHA_TRUONG', 'HOAT_DONG'),
       ('TK005', 'dhsupham@gmail.com', '123456', 'NHA_TRUONG', 'HOAT_DONG'),
       ('TK006', 'dhspkythuat@gmail.com', '123456', 'NHA_TRUONG', 'HOAT_DONG'),
       ('TK007', 'fpt_software@fpt.com.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK008', 'misa@misa.com.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK009', 'axon@axon.com.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK010', 'enclave@enclave.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK011', 'kms@kms-technology.com', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK012', 'neolab@neolab.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK013', 'vnpt@vnpt.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK014', 'viettel@viettel.com.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK015', 'codegym@codegym.vn', '123456', 'NHA_TUYEN_DUNG', 'HOAT_DONG'),
       ('TK016', 'nguyenvana@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK017', 'tranvanb@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK018', 'lethic@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK019', 'phamvand@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK020', 'hoangthie@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK021', 'nguyenvanf@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK022', 'tranthig@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK023', 'levanu@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK024', 'phamthit@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK025', 'huynhvanm@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG'),
       ('TK027', 'dhcntt@dut.udn.vn', '123456', 'NHA_TRUONG', 'KHONG_HOAT_DONG'),
       ('TK028', 'sun@sunsoft.vn', '123456', 'NHA_TUYEN_DUNG', 'KHONG_HOAT_DONG');


-- Thêm dữ liệu vào bảng NHATRUONG
INSERT INTO NHATRUONG (maNhaTruong, maTaiKhoan, tenTruong, diaChi, soDienThoai, maSoThue, bieuTuong, giayPhep)
VALUES ('NT001', 'TK002', 'Đại học Bách Khoa Đà Nẵng', '54 Nguyễn Lương Bằng, Phường Hòa Khánh Bắc, Quận Liên Chiểu', '0236111111',
        '11111111', 'bachkhoa.png', 'giay_phep_NT001.pdf'),
       ('NT002', 'TK003', 'Đại học Kinh tế Đà Nẵng', '71 Nguyễn Văn Linh, Phường Khuê Mỹ, Quận Ngũ Hành Sơn', '0236222222', '22222222',
        'kinhte.png', 'giay_phep_NT002.pdf'),
       ('NT003', 'TK004', 'Đại học Ngoại ngữ Đà Nẵng', '131 Lương Nhữ Hộc, Phường Khuê Trung, Quận Cẩm Lệ', '0236333333',
        '33333333', 'ngoaingu.png', 'giay_phep_NT003.pdf'),
       ('NT004', 'TK005', 'Đại học Sư phạm Đà Nẵng', '459 Tôn Đức Thắng, Phường Hòa Khánh Nam, Quận Liên Chiểu', '0236444444', '44444444',
        'supham.png', 'giay_phep_NT004.pdf'),
       ('NT005', 'TK006', 'Đại học Sư phạm Kỹ thuật Đà Nẵng', '48 Cao Thắng, Phường Thanh Bình, Quận Hải Châu', '0236555555',
        '55555555', 'spkythuat.png', 'giay_phep_NT005.pdf'),
       ('NT006', 'TK027', 'Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn',
        '470 Trần Đại Nghĩa, Phường Hòa Quý, Quận Ngũ Hành Sơn', '0236666666', '66666666', 'viethan.png', 'giay_phep_NT006.pdf');

-- Thêm dữ liệu vào bảng DOANHNGHIEP với địa chỉ chi tiết
INSERT INTO DOANHNGHIEP (
    maDoanhNghiep, maTaiKhoan, tenDoanhNghiep, moHinh, linhVuc,
    diaChi, soDienThoai, maSoThue, trangDoanhNghiep,
    anhDaiDien, moTa, phucLoi, giayPhep
) VALUES
      ('DN001', 'TK007', 'FPT Software Đà Nẵng', 'Công ty cổ phần', 'Công nghệ thông tin',
       'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Phường Hoà Hải, Quận Ngũ Hành Sơn', '0236666666', '66666666', 'fpt.com.vn',
       'fpt.png', 'Công ty phần mềm hàng đầu Việt Nam với hơn 20.000 nhân viên', 'Bảo hiểm, thưởng, đào tạo', 'giay_phep_DN001.pdf'),

      ('DN002', 'TK008', 'MISA Đà Nẵng', 'Công ty TNHH', 'Phần mềm kế toán',
       '162 Nguyễn Văn Linh, Phường Thạc Gián, Quận Thanh Khê', '0236777777', '77777777', 'misa.com.vn',
       'misa.png', 'Công ty chuyên về giải pháp phần mềm kế toán, ERP', 'Chế độ nghỉ mát, bảo hiểm', 'giay_phep_DN002.pdf'),

      ('DN003', 'TK009', 'Axon Đà Nẵng', 'Công ty cổ phần', 'AI và Blockchain',
       '72 Lê Đình Lý, Phường Thạch Thang, Quận Hải Châu', '0236888888', '88888888', 'axon.com.vn',
       'axon.png', 'Công ty chuyên về công nghệ Blockchain, AI, và phát triển phần mềm', 'Thưởng dự án, đào tạo quốc tế', 'giay_phep_DN003.pdf'),

      ('DN004', 'TK010', 'Enclave Đà Nẵng', 'Công ty TNHH', 'Phát triển ứng dụng',
       '295 Nguyễn Văn Linh, Phường Vĩnh Trung, Quận Thanh Khê', '0236999999', '99999999', 'enclave.vn',
       'enclave.png', 'Công ty phát triển ứng dụng di động và web', 'Lương thưởng hấp dẫn, môi trường trẻ', 'giay_phep_DN004.pdf'),

      ('DN005', 'TK011', 'KMS Technology Đà Nẵng', 'Công ty cổ phần', 'Kiểm thử phần mềm',
       '346 Đường 2/9, Phường Hòa Cường Bắc, Quận Hải Châu', '0236101010', '10101010', 'kms-technology.com',
       'kms.png', 'Công ty phát triển phần mềm và kiểm thử tự động', 'Bảo hiểm sức khỏe, lương 13 tháng', 'giay_phep_DN005.pdf'),

      ('DN006', 'TK012', 'NeoLab Đà Nẵng', 'Công ty TNHH', 'IoT',
       '177 Trần Phú, Phường Phước Ninh, Quận Hải Châu', '0236112233', '11223344', 'neolab.vn',
       'neolab.png', 'Công ty chuyên về IoT và phát triển ứng dụng di động', 'Làm việc từ xa, lương thưởng cạnh tranh', 'giay_phep_DN006.pdf'),

      ('DN007', 'TK013', 'VNPT Đà Nẵng', 'Tổng công ty', 'Viễn thông',
       '344 Hoàng Diệu, Phường Bình Hiên, Quận Hải Châu', '0236445566', '44556677', 'vnpt.vn',
       'vnpt.png', 'Tập đoàn viễn thông hàng đầu Việt Nam', 'Chế độ nhà nước, môi trường ổn định', 'giay_phep_DN007.pdf'),

      ('DN008', 'TK014', 'Viettel Đà Nẵng', 'Tập đoàn', 'Viễn thông & Quốc phòng',
       '484 Hoàng Diệu, Phường Bình Thuận, Quận Hải Châu', '0236778899', '77889900', 'viettel.com.vn',
       'viettel.png', 'Tập đoàn viễn thông và công nghệ quốc phòng', 'Phụ cấp quân đội, chế độ ưu đãi', 'giay_phep_DN008.pdf'),

      ('DN009', 'TK015', 'CodeGym Đà Nẵng', 'Công ty cổ phần', 'Đào tạo CNTT',
       '255 Nguyễn Văn Linh, Phường Xuân Hà, Quận Thanh Khê', '0236112299', '11229933', 'codegym.vn',
       'codegym.png', 'Trung tâm đào tạo lập trình viên chuyên nghiệp', 'Môi trường học tập, phát triển bản thân', 'giay_phep_DN009.pdf'),

      ('DN010', 'TK028', 'Sun* Đà Nẵng', 'Công ty TNHH', 'Gia công phần mềm',
       'Block B, Tòa nhà FHome, 16 Lý Thường Kiệt, Phường Thạch Thang, Quận Hải Châu',
       '02363888888', '55667788', 'sun-asterisk.vn',
       'sun.png', 'Công ty Nhật Bản chuyên phát triển phần mềm theo mô hình Agile', 'Đào tạo, mentor, môi trường trẻ', 'giay_phep_DN010.pdf');

-- Thêm dữ liệu vào bảng SINHVIEN
INSERT INTO SINHVIEN (maSinhVien, maTaiKhoan, maNhaTruong, hoVaTen, ngaySinh, soDienThoai, gioiTinh, diaChi, khoa, lop,
                      chuyenNganh, CCCD, trangThai)
VALUES ('SV001', 'TK016', 'NT001', 'Nguyễn Văn A', '2002-05-10', '0901111111', 'NAM', '123 Nguyễn Tri Phương, Đà Nẵng',
        'Công nghệ thông tin', 'CNTT1', 'Kỹ thuật phần mềm', '123456789001', 'DUNG'),
       ('SV002', 'TK017', 'NT001', 'Trần Văn B', '2002-06-15', '0902222222', 'NAM', '456 Lê Duẩn, Đà Nẵng',
        'Công nghệ thông tin', 'CNTT2', 'An toàn thông tin', '123456789002', 'DUNG'),
       ('SV003', 'TK018', 'NT002', 'Lê Thị C', '2003-07-20', '0903333333', 'NU', '789 Trần Cao Vân, Đà Nẵng',
        'Quản trị kinh doanh', 'QTKD1', 'Marketing', '123456789003', 'DUNG'),
       ('SV004', 'TK019', 'NT002', 'Phạm Văn D', '2003-08-25', '0904444444', 'NAM', '321 Trần Phú, Đà Nẵng', 'Kế toán',
        'KT1', 'Kế toán doanh nghiệp', '123456789004', 'DUNG'),
       ('SV005', 'TK020', 'NT003', 'Hoàng Thị E', '2002-09-30', '0905555555', 'NU', '654 Hà Huy Tập, Đà Nẵng',
        'Tiếng Anh', 'TA1', 'Biên phiên dịch', '123456789005', 'DUNG'),
       ('SV006', 'TK021', 'NT003', 'Nguyễn Văn F', '2002-10-05', '0906666666', 'NAM', '987 Điện Biên Phủ, Đà Nẵng',
        'Tiếng Nhật', 'TN1', 'Ngôn ngữ Nhật', '123456789006', 'DUNG'),
       ('SV007', 'TK022', 'NT004', 'Trần Thị G', '2003-11-10', '0907777777', 'NU', '159 Hùng Vương, Đà Nẵng',
        'Sư phạm Toán', 'SPT1', 'Sư phạm Toán học', '123456789007', 'DUNG'),
       ('SV008', 'TK023', 'NT004', 'Lê Văn H', '2003-12-15', '0908888888', 'NAM', '753 Lê Đình Lý, Đà Nẵng',
        'Sư phạm Vật lý', 'SPV1', 'Sư phạm Vật lý', '123456789008', 'DUNG'),
       ('SV009', 'TK024', 'NT005', 'Phạm Thị T', '2002-01-20', '0909999999', 'NU', '951 Ngô Quyền, Đà Nẵng',
        'Điện-Điện tử', 'DDT1', 'Điện tử viễn thông', '123456789009', 'DUNG'),
       ('SV010', 'TK025', 'NT005', 'Huỳnh Văn M', '2002-02-25', '0900000000', 'NAM', '357 Quang Trung, Đà Nẵng', 'Cơ khí',
        'CK1', 'Cơ khí chế tạo', '123456789010', 'DUNG');

-- Thêm dữ liệu vào bảng DANHMUC
INSERT INTO DANHMUC (maDanhMuc, tenDanhMuc)
VALUES ('DM001', 'Công nghệ thông tin'),
       ('DM002', 'Kế toán - Kiểm toán'),
       ('DM003', 'Marketing - Truyền thông'),
       ('DM004', 'Kỹ thuật - Cơ khí'),
       ('DM005', 'Ngoại ngữ'),
       ('DM006', 'Nhân sự - Hành chính'),
       ('DM007', 'Bán hàng - Kinh doanh'),
       ('DM008', 'Tài chính - Ngân hàng'),
       ('DM009', 'Thiết kế - Đồ họa'),
       ('DM010', 'Du lịch - Nhà hàng - Khách sạn');

-- Thêm dữ liệu vào bảng KYNANG
INSERT INTO KYNANG (maKyNang, maDanhMuc, tenKyNang)
VALUES ('KN001', 'DM001', 'Lập trình Java'),
       ('KN002', 'DM001', 'Lập trình Python'),
       ('KN003', 'DM001', 'Lập trình C/C++'),
       ('KN004', 'DM001', 'Lập trình PHP'),
       ('KN005', 'DM001', 'Frontend (HTML, CSS, JavaScript)'),
       ('KN006', 'DM001', 'ReactJS'),
       ('KN007', 'DM001', 'NodeJS'),
       ('KN008', 'DM001', 'SQL Database'),
       ('KN009', 'DM001', 'NoSQL Database'),
       ('KN010', 'DM001', 'DevOps'),
       ('KN011', 'DM002', 'Kế toán tổng hợp'),
       ('KN012', 'DM002', 'Phân tích báo cáo tài chính'),
       ('KN013', 'DM003', 'Digital Marketing'),
       ('KN014', 'DM003', 'Content Marketing'),
       ('KN015', 'DM004', 'Vẽ kỹ thuật'),
       ('KN016', 'DM004', 'Thiết kế 3D'),
       ('KN017', 'DM005', 'Tiếng Anh giao tiếp'),
       ('KN018', 'DM005', 'Tiếng Nhật N3'),
       ('KN019', 'DM009', 'Photoshop'),
       ('KN020', 'DM009', 'Illustrator');

INSERT INTO BAIDANG (maBaiDang, maDoanhNghiep, maDanhMuc, tieuDe, diaChi, yeuCau, moTa, denHan, quyenLoi, loai, trangThai)
VALUES
    ('BD001', 'DN001', 'DM001', 'Thực tập sinh Java Developer', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'Có kiến thức cơ bản về Java; Spring Framework. GPA >= 3.0', 'Tham gia phát triển các dự án web application sử dụng Java Spring Boot', '2025-07-30 23:59:59', 'Trợ cấp 3-5 triệu/tháng; được mentor 1-1; cơ hội full-time', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD002', 'DN001', 'DM001', 'Thực tập sinh Frontend Developer', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'Thành thạo HTML, CSS, JavaScript. Biết ReactJS là lợi thế', 'Phát triển giao diện người dùng cho các ứng dụng web hiện đại', '2025-08-15 23:59:59', 'Trợ cấp 2-4 triệu/tháng; đào tạo công nghệ mới', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD003', 'DN002', 'DM002', 'Thực tập sinh Kế toán', '162 Nguyễn Văn Linh, Phường Thạc Gián, Đà Nẵng', 'Sinh viên năm 3-4 ngành Kế toán. Thành thạo Excel', 'Hỗ trợ công việc kế toán tổng hợp, lập báo cáo tài chính', '2025-06-30 23:59:59', 'Trợ cấp 2-3 triệu/tháng, học hỏi từ kế toán trưởng', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD004', 'DN003', 'DM001', 'Thực tập sinh AI/ML Engineer', '72 Lê Đình Lý, Phường Thạch Thang, Đà Nẵng', 'Kiến thức Python, Machine Learning cơ bản. Biết TensorFlow/PyTorch', 'Nghiên cứu và phát triển các mô hình AI cho sản phẩm công ty', '2025-09-01 23:59:59', 'Trợ cấp 4-6 triệu/tháng, tham gia dự án thực tế', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD005', 'DN004', 'DM001', 'Thực tập sinh Mobile Developer', '295 Nguyễn Văn Linh, Phường Vĩnh Trung, Đà Nẵng', 'Có kinh nghiệm React Native hoặc Flutter. Biết Git', 'Phát triển ứng dụng di động cho iOS và Android', '2025-07-15 23:59:59', 'Trợ cấp 3-5 triệu/tháng, môi trường startup năng động', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD006', 'DN005', 'DM001', 'Thực tập sinh QA Tester', '346 Đường 2/9, Phường Hòa Cường Bắc, Đà Nẵng', 'Tỉ mỉ, cẩn thận. Có hiểu biết về quy trình test', 'Thực hiện test manual và automation cho các sản phẩm phần mềm', '2025-08-31 23:59:59', 'Trợ cấp 2-4 triệu/tháng, đào tạo automation testing', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD007', 'DN006', 'DM001', 'Thực tập sinh IoT Developer', '177 Trần Phú, Phường Phước Ninh, Đà Nẵng', 'Kiến thức về Arduino, Raspberry Pi. Biết C/C++', 'Phát triển các giải pháp IoT cho smart home, smart city', '2025-06-15 23:59:59', 'Trợ cấp 3-4 triệu/tháng, làm việc với thiết bị hiện đại', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD008', 'DN007', 'DM001', 'Thực tập sinh Network Engineer', '344 Hoàng Diệu, Phường Bình Hiên, Đà Nẵng', 'Kiến thức mạng máy tính, CCNA là lợi thế', 'Hỗ trợ vận hành hệ thống mạng viễn thông', '2025-07-31 23:59:59', 'Trợ cấp 3-5 triệu/tháng, chế độ nhà nước', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD009', 'DN008', 'DM001', 'Thực tập sinh Cybersecurity', '484 Hoàng Diệu, Phường Bình Thuận, Đà Nẵng', 'Hiểu biết về bảo mật thông tin, ethical hacking', 'Tham gia đội ngũ bảo mật, phân tích lỗ hổng bảo mật', '2025-08-15 23:59:59', 'Trợ cấp 4-6 triệu/tháng, đào tạo chuyên sâu', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD010', 'DN009', 'DM001', 'Thực tập sinh Giảng viên CNTT', '255 Nguyễn Văn Linh, Phường Xuân Hà, Đà Nẵng', 'Kỹ năng thuyết trình tốt, kiến thức lập trình vững', 'Hỗ trợ giảng dạy các khóa học lập trình', '2025-09-30 23:59:59', 'Trợ cấp 2-3 triệu/tháng, kinh nghiệm giảng dạy', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD011', 'DN002', 'DM003', 'Thực tập sinh Marketing Digital', '162 Nguyễn Văn Linh, Phường Thạc Gián, Đà Nẵng', 'Hiểu biết về Facebook Ads, Google Ads. Biết Photoshop', 'Triển khai các chiến dịch marketing online cho sản phẩm', '2025-07-01 23:59:59', 'Trợ cấp 2-4 triệu/tháng, học marketing thực chiến', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD012', 'DN003', 'DM001', 'Thực tập sinh Blockchain Developer', '72 Lê Đình Lý, Phường Thạch Thang, Đà Nẵng', 'Kiến thức về Blockchain, Smart Contract. Biết Solidity', 'Phát triển ứng dụng phi tập trung (DApp) trên Ethereum', '2025-08-30 23:59:59', 'Trợ cấp 5-7 triệu/tháng, công nghệ tiên tiến', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD013', 'DN004', 'DM009', 'Thực tập sinh UI/UX Designer', '295 Nguyễn Văn Linh, Phường Vĩnh Trung, Đà Nẵng', 'Thành thạo Figma, Adobe XD. Có portfolio design', 'Thiết kế giao diện và trải nghiệm người dùng cho app', '2025-06-20 23:59:59', 'Trợ cấp 2-4 triệu/tháng, mentor design senior', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD014', 'DN005', 'DM001', 'Thực tập sinh DevOps Engineer', '346 Đường 2/9, Phường Hòa Cường Bắc, Đà Nẵng', 'Kiến thức Linux, Docker, CI/CD. Biết AWS là lợi thế', 'Xây dựng và duy trì hệ thống CI/CD, cloud infrastructure', '2025-09-15 23:59:59', 'Trợ cấp 4-6 triệu/tháng, công nghệ cloud', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD015', 'DN006', 'DM004', 'Thực tập sinh Embedded Engineer', '177 Trần Phú, Phường Phước Ninh, Đà Nẵng', 'Kiến thức vi điều khiển, lập trình C. Biết PCB design', 'Phát triển firmware cho các thiết bị IoT', '2025-07-20 23:59:59', 'Trợ cấp 3-5 triệu/tháng, làm việc với hardware', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD016', 'DN007', 'DM006', 'Thực tập sinh Nhân sự', '344 Hoàng Diệu, Phường Bình Hiên, Đà Nẵng', 'Kỹ năng giao tiếp tốt, sử dụng thành thạo MS Office', 'Hỗ trợ tuyển dụng, đào tạo và quản lý nhân sự', '2025-08-10 23:59:59', 'Trợ cấp 2-3 triệu/tháng, kinh nghiệm HR', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD017', 'DN008', 'DM008', 'Thực tập sinh Phân tích tài chính', '484 Hoàng Diệu, Phường Bình Thuận, Đà Nẵng', 'Kiến thức tài chính, thành thạo Excel, PowerBI', 'Phân tích báo cáo tài chính, đánh giá hiệu quả đầu tư', '2025-07-25 23:59:59', 'Trợ cấp 3-4 triệu/tháng, chế độ tập đoàn', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD018', 'DN009', 'DM003', 'Thực tập sinh Content Creator', '255 Nguyễn Văn Linh, Phường Xuân Hà, Đà Nẵng', 'Kỹ năng viết lách tốt, biết làm video. Sáng tạo', 'Tạo nội dung cho website, social media của trung tâm', '2025-06-30 23:59:59', 'Trợ cấp 2-3 triệu/tháng, phát triển kỹ năng content', 'BAN_THOI_GIAN', 'CON_HAN'),

    ('BD019', 'DN001', 'DM001', 'Thực tập sinh Data Analyst', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'Kiến thức SQL, Python, Excel. Biết Tableau/PowerBI', 'Phân tích dữ liệu business, tạo dashboard báo cáo', '2025-08-20 23:59:59', 'Trợ cấp 3-5 triệu/tháng, làm việc với big data', 'TOAN_THOI_GIAN', 'CON_HAN'),

    ('BD020', 'DN002', 'DM007', 'Thực tập sinh Kinh doanh B2B', '162 Nguyễn Văn Linh, Phường Thạc Gián, Đà Nẵng', 'Kỹ năng giao tiếp, thuyết phục tốt. Ham học hỏi', 'Hỗ trợ team sales trong việc chăm sóc khách hàng doanh nghiệp', '2025-09-10 23:59:59', 'Trợ cấp 2-4 triệu/tháng + hoa hồng', 'BAN_THOI_GIAN', 'CON_HAN');

-- Thêm dữ liệu vào bảng KYNANG_BAIDANG (20 liên kết kỹ năng - bài đăng)
INSERT INTO KYNANG_BAIDANG (maKyNang, maBaiDang)
VALUES
    ('KN001', 'BD001'), ('KN008', 'BD001'),
    ('KN005', 'BD002'), ('KN006', 'BD002'),
    ('KN011', 'BD003'), ('KN012', 'BD003'),
    ('KN002', 'BD004'), ('KN008', 'BD004'),
    ('KN005', 'BD005'), ('KN006', 'BD005'),
    ('KN008', 'BD006'), ('KN010', 'BD006'),
    ('KN003', 'BD007'), ('KN008', 'BD007'),
    ('KN008', 'BD008'), ('KN010', 'BD008'),
    ('KN008', 'BD009'), ('KN010', 'BD009'),
    ('KN001', 'BD010'), ('KN002', 'BD010'),
    ('KN013', 'BD011'), ('KN019', 'BD011'),
    ('KN001', 'BD012'), ('KN008', 'BD012'),
    ('KN019', 'BD013'), ('KN020', 'BD013'),
    ('KN010', 'BD014'), ('KN008', 'BD014'),
    ('KN003', 'BD015'), ('KN015', 'BD015'),
    ('KN017', 'BD016'), ('KN011', 'BD016'),
    ('KN012', 'BD017'), ('KN008', 'BD017'),
    ('KN014', 'BD018'), ('KN019', 'BD018'),
    ('KN002', 'BD019'), ('KN008', 'BD019'),
    ('KN013', 'BD020'), ('KN014', 'BD020');

-- Thêm dữ liệu vào bảng SINHVIEN_BAIDANG (20 đơn ứng tuyển)
INSERT INTO SINHVIEN_BAIDANG (maSVBD, maSinhVien, maBaiDang, soYeuLyLich, ketQua)
VALUES
    ('SVBD001', 'SV001', 'BD001', 'CV_NguyenVanA_Java.pdf', 'THONG_QUA'),
    ('SVBD002', 'SV001', 'BD002', 'CV_NguyenVanA_Frontend.pdf', 'DANG_CHO'),
    ('SVBD003', 'SV002', 'BD001', 'CV_TranVanB_Java.pdf', 'CHO_PHONG_VAN'),
    ('SVBD004', 'SV002', 'BD009', 'CV_TranVanB_Security.pdf', 'DANG_CHO'),
    ('SVBD005', 'SV003', 'BD003', 'CV_LeThiC_KeToan.pdf', 'THONG_QUA'),
    ('SVBD006', 'SV003', 'BD011', 'CV_LeThiC_Marketing.pdf', 'DANG_CHO'),
    ('SVBD007', 'SV004', 'BD003', 'CV_PhamVanD_KeToan.pdf', 'CHO_PHONG_VAN'),
    ('SVBD008', 'SV004', 'BD017', 'CV_PhamVanD_TaiChinh.pdf', 'DANG_CHO'),
    ('SVBD009', 'SV005', 'BD016', 'CV_HoangThiE_NhanSu.pdf', 'THONG_QUA'),
    ('SVBD010', 'SV005', 'BD018', 'CV_HoangThiE_Content.pdf', 'DANG_CHO'),
    ('SVBD011', 'SV006', 'BD016', 'CV_NguyenVanF_NhanSu.pdf', 'TU_CHOI'),
    ('SVBD012', 'SV007', 'BD010', 'CV_TranThiG_GiangVien.pdf', 'CHO_PHONG_VAN'),
    ('SVBD013', 'SV008', 'BD015', 'CV_LeVanH_Embedded.pdf', 'DANG_CHO'),
    ('SVBD014', 'SV009', 'BD007', 'CV_PhamThiT_IoT.pdf', 'THONG_QUA'),
    ('SVBD015', 'SV009', 'BD015', 'CV_PhamThiT_Embedded.pdf', 'DANG_CHO'),
    ('SVBD016', 'SV010', 'BD015', 'CV_HuynhVanM_Embedded.pdf', 'CHO_PHONG_VAN'),
    ('SVBD017', 'SV001', 'BD019', 'CV_NguyenVanA_DataAnalyst.pdf', 'DANG_CHO'),
    ('SVBD018', 'SV002', 'BD014', 'CV_TranVanB_DevOps.pdf', 'TU_CHOI'),
    ('SVBD019', 'SV003', 'BD020', 'CV_LeThiC_Sales.pdf', 'DANG_CHO'),
    ('SVBD020', 'SV004', 'BD012', 'CV_PhamVanD_Blockchain.pdf', 'CHO_PHONG_VAN');

-- Thêm dữ liệu vào bảng LICHPHONGVAN (20 lịch phỏng vấn)
INSERT INTO LICHPHONGVAN (maLichPhongVan, maSinhVien, maDoanhNghiep, maSVBD, viTriPhongVan, ngayPhongVan, diaDiem, hinhThucPhongVan, trangThai, soYeuLyLich, hanXacNhan)
VALUES
    ('LPV001', 'SV002', 'DN001', 'SVBD003', 'Java Developer Intern', '2025-06-13 19:00:00', 'Phòng họp A1, Tòa nhà FPT Complex', 'TRUC_TIEP', 'DONG_Y', 'CV_TranVanB_Java.pdf', '2025-06-03 17:00:00'),
    ('LPV002', 'SV004', 'DN002', 'SVBD007', 'Thực tập sinh Kế toán', '2025-06-06 09:00:00', 'Phòng nhân sự, MISA Đà Nẵng', 'TRUC_TIEP', 'DANG_CHO', 'CV_PhamVanD_KeToan.pdf', '2025-06-04 17:00:00'),
    ('LPV003', 'SV007', 'DN009', 'SVBD012', 'Thực tập Giảng viên', '2025-06-07 10:30:00', 'CodeGym Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', 'CV_TranThiG_GiangVien.pdf', '2025-06-05 17:00:00'),
    ('LPV004', 'SV010', 'DN006', 'SVBD016', 'Embedded Engineer Intern', '2025-06-08 15:00:00', 'Link Zoom: zoom.us/j/123456', 'TRUC_TUYEN', 'DANG_CHO', 'CV_HuynhVanM_Embedded.pdf', '2025-06-06 17:00:00'),
    ('LPV005', 'SV004', 'DN002', 'SVBD020', 'Blockchain Developer', '2025-06-10 14:30:00', 'Axon Office, Lê Đình Lý', 'TRUC_TIEP', 'DONG_Y', 'CV_PhamVanD_Blockchain.pdf', '2025-06-08 17:00:00'),
    ('LPV006', 'SV001', 'DN001', 'SVBD001', 'Java Developer Review', '2025-06-12 16:00:00', 'Phòng họp B2, FPT Complex', 'TRUC_TIEP', 'HOAN_THANH', 'CV_NguyenVanA_Java.pdf', '2025-06-10 17:00:00'),
    ('LPV007', 'SV003', 'DN002', 'SVBD005', 'Kế toán Final Interview', '2025-06-13 10:00:00', 'MISA Head Office', 'TRUC_TIEP', 'HOAN_THANH', 'CV_LeThiC_KeToan.pdf', '2025-06-11 17:00:00'),
    ('LPV008', 'SV005', 'DN007', 'SVBD009', 'HR Intern Interview', '2025-06-14 11:00:00', 'VNPT Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', 'CV_HoangThiE_NhanSu.pdf', '2025-06-12 17:00:00'),
    ('LPV009', 'SV009', 'DN006', 'SVBD014', 'IoT Developer Talk', '2025-06-15 09:30:00', 'Google Meet: meet.google.com/abc-def', 'TRUC_TUYEN', 'HOAN_THANH', 'CV_PhamThiT_IoT.pdf', '2025-06-13 17:00:00'),
    ('LPV010', 'SV002', 'DN008', 'SVBD004', 'Cybersecurity Interview', '2025-06-16 14:00:00', 'Viettel Cyber Security Dept', 'TRUC_TIEP', 'DANG_CHO', 'CV_TranVanB_Security.pdf', '2025-06-14 17:00:00'),
    ('LPV011', 'SV008', 'DN006', 'SVBD013', 'Embedded System Talk', '2025-06-17 13:30:00', 'NeoLab Office', 'TRUC_TIEP', 'DONG_Y', 'CV_LeVanH_Embedded.pdf', '2025-06-15 17:00:00'),
    ('LPV012', 'SV001', 'DN001', 'SVBD017', 'Data Analyst Position', '2025-06-18 15:30:00', 'Teams Meeting', 'TRUC_TUYEN', 'DANG_CHO', 'CV_NguyenVanA_DataAnalyst.pdf', '2025-06-16 17:00:00'),
    ('LPV013', 'SV003', 'DN002', 'SVBD019', 'B2B Sales Interview', '2025-06-19 10:15:00', 'MISA Sales Department', 'TRUC_TIEP', 'DONG_Y', 'CV_LeThiC_Sales.pdf', '2025-06-17 17:00:00'),
    ('LPV014', 'SV006', 'DN007', 'SVBD011', 'HR Position - Round 2', '2025-06-20 11:45:00', 'VNPT HR Office', 'TRUC_TIEP', 'TU_CHOI', 'CV_NguyenVanF_NhanSu.pdf', '2025-06-18 17:00:00'),
    ('LPV015', 'SV005', 'DN009', 'SVBD010', 'Content Creator Role', '2025-06-21 14:15:00', 'CodeGym Content Team', 'TRUC_TIEP', 'DANG_CHO', 'CV_HoangThiE_Content.pdf', '2025-06-19 17:00:00'),
    ('LPV016', 'SV002', 'DN005', 'SVBD018', 'DevOps Engineer Interview', '2025-06-22 09:45:00', 'Skype Interview', 'TRUC_TUYEN', 'TU_CHOI', 'CV_TranVanB_DevOps.pdf', '2025-06-20 17:00:00'),
    ('LPV017', 'SV009', 'DN006', 'SVBD015', 'Embedded - Technical Round', '2025-06-23 16:30:00', 'NeoLab Lab Room', 'TRUC_TIEP', 'DONG_Y', 'CV_PhamThiT_Embedded.pdf', '2025-06-21 17:00:00'),
    ('LPV018', 'SV003', 'DN002', 'SVBD006', 'Digital Marketing Interview', '2025-06-24 13:00:00', 'MISA Marketing Dept', 'TRUC_TIEP', 'DANG_CHO', 'CV_LeThiC_Marketing.pdf', '2025-06-22 17:00:00'),
    ('LPV019', 'SV004', 'DN008', 'SVBD008', 'Financial Analyst Talk', '2025-06-25 10:30:00', 'Viettel Finance Office', 'TRUC_TIEP', 'DONG_Y', 'CV_PhamVanD_TaiChinh.pdf', '2025-06-23 17:00:00'),
    ('LPV020', 'SV001', 'DN001', 'SVBD002', 'Frontend Developer Review', '2025-06-26 15:00:00', 'Zoom Meeting Room', 'TRUC_TUYEN', 'DANG_CHO', 'CV_NguyenVanA_Frontend.pdf', '2025-06-24 17:00:00');

-- Thêm dữ liệu vào bảng LOIMOITHUCTAP
INSERT INTO LOIMOITHUCTAP (maLMTT, maSinhVien, maDoanhNghiep, viTriThucTap, maBaiDang, tuNgay, denNgay, diaChi, loaiThucTap, quyenLoi, trangThai, hanXacNhan)
VALUES
    ('LMTT001', 'SV001', 'DN001', 'Java Developer Intern', 'BD001', '2025-07-01', '2025-09-30', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4 triệu/tháng, mentor 1-1, cơ hội full-time sau thực tập', 'CHAP_NHAN', '2025-06-15 17:00:00'),

    ('LMTT002', 'SV003', 'DN002', 'Kế toán Intern', 'BD003', '2025-07-15', '2025-10-15', '162 Nguyễn Văn Linh, Phường Thạc Gián, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 2.5 triệu/tháng, học hỏi thực tế kế toán doanh nghiệp', 'CHAP_NHAN', '2025-06-20 17:00:00'),

    ('LMTT003', 'SV005', 'DN007', 'HR Intern', 'BD016', '2025-06-20', '2025-08-20', '344 Hoàng Diệu, Phường Bình Hiên, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 2.8 triệu/tháng, chế độ nhà nước, kinh nghiệm HR', 'CHAP_NHAN', '2025-06-18 17:00:00'),

    ('LMTT004', 'SV009', 'DN006', 'IoT Developer Intern', 'BD007', '2025-08-01', '2025-11-30', '177 Trần Phú, Phường Phước Ninh, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 3.5 triệu/tháng, làm việc với thiết bị IoT hiện đại', 'CHAP_NHAN', '2025-07-15 17:00:00'),

    ('LMTT005', 'SV002', 'DN008', 'Cybersecurity Intern', 'BD009', '2025-07-01', '2025-12-31', '484 Hoàng Diệu, Phường Bình Thuận, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 5 triệu/tháng, đào tạo chuyên sâu bảo mật', 'DANG_CHO', '2025-06-25 17:00:00'),

    ('LMTT006', 'SV007', 'DN009', 'Teaching Assistant', 'BD010', '2025-06-15', '2025-12-15', '255 Nguyễn Văn Linh, Phường Xuân Hà, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 2.5 triệu/tháng, kinh nghiệm giảng dạy quý giá', 'CHAP_NHAN', '2025-06-10 17:00:00'),

    ('LMTT007', 'SV004', 'DN003', 'Blockchain Developer Intern', 'BD012', '2025-08-15', '2025-11-15', '72 Lê Đình Lý, Phường Thạch Thang, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 6 triệu/tháng, học công nghệ Blockchain tiên tiến', 'DANG_CHO', '2025-07-20 17:00:00'),

    ('LMTT008', 'SV008', 'DN006', 'Embedded Engineer Intern', 'BD015', '2025-07-20', '2025-10-20', '177 Trần Phú, Phường Phước Ninh, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4 triệu/tháng, làm việc với vi điều khiển', 'CHAP_NHAN', '2025-07-05 17:00:00'),

    ('LMTT009', 'SV001', 'DN001', 'Frontend Developer Intern', 'BD002', '2025-09-01', '2025-12-01', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 3.5 triệu/tháng, học ReactJS từ senior developer', 'DANG_CHO', '2025-08-15 17:00:00'),

    ('LMTT010', 'SV010', 'DN005', 'QA Tester Intern', 'BD006', '2025-07-10', '2025-09-10', '346 Đường 2/9, Phường Hòa Cường Bắc, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 3 triệu/tháng, đào tạo automation testing', 'CHAP_NHAN', '2025-06-25 17:00:00'),

    ('LMTT011', 'SV003', 'DN002', 'Digital Marketing Intern', 'BD011', '2025-08-01', '2025-10-31', '162 Nguyễn Văn Linh, Phường Thạc Gián, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 3 triệu/tháng, học marketing thực chiến', 'DANG_CHO', '2025-07-10 17:00:00'),

    ('LMTT012', 'SV006', 'DN004', 'UI/UX Designer Intern', 'BD013', '2025-07-05', '2025-09-05', '295 Nguyễn Văn Linh, Phường Vĩnh Trung, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 3.2 triệu/tháng, mentor từ senior designer', 'CHAP_NHAN', '2025-06-20 17:00:00'),

    ('LMTT013', 'SV002', 'DN005', 'DevOps Engineer Intern', 'BD014', '2025-09-15', '2025-12-15', '346 Đường 2/9, Phường Hòa Cường Bắc, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 5 triệu/tháng, học AWS và Docker', 'TU_CHOI', '2025-08-20 17:00:00'),

    ('LMTT014', 'SV004', 'DN008', 'Financial Analyst Intern', 'BD017', '2025-08-01', '2025-11-01', '484 Hoàng Diệu, Phường Bình Thuận, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 3.5 triệu/tháng, chế độ tập đoàn', 'CHAP_NHAN', '2025-07-15 17:00:00'),

    ('LMTT015', 'SV005', 'DN009', 'Content Creator Intern', 'BD018', '2025-07-01', '2025-09-30', '255 Nguyễn Văn Linh, Phường Xuân Hà, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 2.8 triệu/tháng, phát triển kỹ năng content', 'CHAP_NHAN', '2025-06-15 17:00:00'),

    ('LMTT016', 'SV001', 'DN001', 'Data Analyst Intern', 'BD019', '2025-08-20', '2025-11-20', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4.5 triệu/tháng, làm việc với big data', 'DANG_CHO', '2025-08-05 17:00:00'),

    ('LMTT017', 'SV003', 'DN002', 'Sales Intern B2B', 'BD020', '2025-09-10', '2025-12-10', '162 Nguyễn Văn Linh, Phường Thạc Gián, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 3 triệu/tháng + hoa hồng theo doanh số', 'DANG_CHO', '2025-08-25 17:00:00'),

    ('LMTT018', 'SV009', 'DN004', 'Mobile Developer Intern', 'BD005', '2025-07-15', '2025-10-15', '295 Nguyễn Văn Linh, Phường Vĩnh Trung, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4 triệu/tháng, học React Native', 'CHAP_NHAN', '2025-07-01 17:00:00'),

    ('LMTT019', 'SV010', 'DN007', 'Network Engineer Intern', 'BD008', '2025-08-01', '2025-10-31', '344 Hoàng Diệu, Phường Bình Hiên, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4.2 triệu/tháng, học về hạ tầng mạng', 'CHAP_NHAN', '2025-07-20 17:00:00'),

    ('LMTT020', 'SV006', 'DN003', 'AI/ML Engineer Intern', 'BD004', '2025-09-01', '2025-12-31', '72 Lê Đình Lý, Phường Thạch Thang, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 5.5 triệu/tháng, tham gia dự án AI thực tế', 'DANG_CHO', '2025-08-15 17:00:00'),

    ('LMTT021', 'SV008', 'DN001', 'Java Developer Intern - Advanced', 'BD001', '2025-08-15', '2025-11-15', 'Tòa nhà FPT Complex, đường Nam Kỳ Khởi Nghĩa, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4.5 triệu/tháng, dự án microservices', 'CHAP_NHAN', '2025-08-01 17:00:00'),

    ('LMTT022', 'SV007', 'DN005', 'Software Tester Intern', 'BD006', '2025-07-01', '2025-09-30', '346 Đường 2/9, Phường Hòa Cường Bắc, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 3.2 triệu/tháng, học Selenium automation', 'TU_CHOI', '2025-06-20 17:00:00'),

    ('LMTT023', 'SV002', 'DN006', 'Firmware Developer Intern', 'BD015', '2025-08-10', '2025-11-10', '177 Trần Phú, Phường Phước Ninh, Đà Nẵng', 'TOAN_THOI_GIAN', 'Trợ cấp 4.3 triệu/tháng, phát triển firmware IoT', 'DANG_CHO', '2025-07-25 17:00:00'),

    ('LMTT024', 'SV004', 'DN009', 'Programming Instructor Assistant', 'BD010', '2025-06-01', '2025-08-31', '255 Nguyễn Văn Linh, Phường Xuân Hà, Đà Nẵng', 'BAN_THOI_GIAN', 'Trợ cấp 2.7 triệu/tháng, kinh nghiệm giảng dạy', 'CHAP_NHAN', '2025-05-20 17:00:00');