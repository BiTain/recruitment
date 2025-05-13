USE tuyendung_tts;

INSERT INTO TAIKHOAN (maTaiKhoan, email, matKhau, vaiTro, trangThai)
VALUES ('TK026', 'bao@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG');
-- Thêm dữ liệu vào bảng TAIKHOAN
INSERT INTO TAIKHOAN (maTaiKhoan, email, matKhau, vaiTro, trangThai)
VALUES ('TK001', 'admin@admin.com', '123456', 'ADMIN', 'HOAT_DONG'),
       ('TK002', 'dhbachkhoa@gmail.com', '123456', 'NHA_TRUONG', 'HOAT_DONG'),
       ('TK003', 'dhkinhtedanang@gmail.com', '123456', 'NHA_TRUONG', 'HOAT_DONG'),
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
       ('TK025', 'huynhvanm@gmail.com', '123456', 'SINH_VIEN', 'HOAT_DONG');

-- Thêm dữ liệu vào bảng NHATRUONG
INSERT INTO NHATRUONG (maNhaTruong, maTaiKhoan, tenTruong, diaChi, soDienThoai, maSoThue, bieuTuong)
VALUES ('NT001', 'TK002', 'Đại học Bách Khoa Đà Nẵng', '54 Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng', '0236111111',
        '11111111', 'bachkhoa.png'),
       ('NT002', 'TK003', 'Đại học Kinh tế Đà Nẵng', '71 Ngũ Hành Sơn, Đà Nẵng', '0236222222', '22222222',
        'kinhte.png'),
       ('NT003', 'TK004', 'Đại học Ngoại ngữ Đà Nẵng', '131 Lương Nhữ Hộc, Khuê Trung, Cẩm Lệ, Đà Nẵng', '0236333333',
        '33333333', 'ngoaingu.png'),
       ('NT004', 'TK005', 'Đại học Sư phạm Đà Nẵng', '459 Tôn Đức Thắng, Liên Chiểu, Đà Nẵng', '0236444444', '44444444',
        'supham.png'),
       ('NT005', 'TK006', 'Đại học Sư phạm Kỹ thuật Đà Nẵng', '48 Cao Thắng, Hải Châu, Đà Nẵng', '0236555555',
        '55555555', 'spkythuat.png');

-- Thêm dữ liệu vào bảng DOANHNGHIEP
INSERT INTO DOANHNGHIEP (
    maDoanhNghiep, maTaiKhoan, tenDoanhNghiep, moHinh, linhVuc,
    diaChi, soDienThoai, maSoThue, trangDoanhNghiep,
    anhDaiDien, moTa, phucLoi
) VALUES
      ('DN001', 'TK007', 'FPT Software Đà Nẵng', 'Công ty cổ phần', 'Công nghệ thông tin',
       'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng', '0236666666', '66666666', 'fpt.com.vn',
       'fpt.png', 'Công ty phần mềm hàng đầu Việt Nam với hơn 20.000 nhân viên', 'Bảo hiểm, thưởng, đào tạo'),

      ('DN002', 'TK008', 'MISA Đà Nẵng', 'Công ty TNHH', 'Phần mềm kế toán',
       '162 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', '0236777777', '77777777', 'misa.com.vn',
       'misa.png', 'Công ty chuyên về giải pháp phần mềm kế toán, ERP', 'Chế độ nghỉ mát, bảo hiểm'),

      ('DN003', 'TK009', 'Axon Đà Nẵng', 'Công ty cổ phần', 'AI và Blockchain',
       '72 Lê Đình Lý, Hải Châu, Đà Nẵng', '0236888888', '88888888', 'axon.com.vn',
       'axon.png', 'Công ty chuyên về công nghệ Blockchain, AI, và phát triển phần mềm', 'Thưởng dự án, đào tạo quốc tế'),

      ('DN004', 'TK010', 'Enclave Đà Nẵng', 'Công ty TNHH', 'Phát triển ứng dụng',
       '295 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', '0236999999', '99999999', 'enclave.vn',
       'enclave.png', 'Công ty phát triển ứng dụng di động và web', 'Lương thưởng hấp dẫn, môi trường trẻ'),

      ('DN005', 'TK011', 'KMS Technology Đà Nẵng', 'Công ty cổ phần', 'Kiểm thử phần mềm',
       '346 2/9, Hải Châu, Đà Nẵng', '0236101010', '10101010', 'kms-technology.com',
       'kms.png', 'Công ty phát triển phần mềm và kiểm thử tự động', 'Bảo hiểm sức khỏe, lương 13 tháng'),

      ('DN006', 'TK012', 'NeoLab Đà Nẵng', 'Công ty TNHH', 'IoT',
       '177 Trần Phú, Hải Châu, Đà Nẵng', '0236112233', '11223344', 'neolab.vn',
       'neolab.png', 'Công ty chuyên về IoT và phát triển ứng dụng di động', 'Làm việc từ xa, lương thưởng cạnh tranh'),

      ('DN007', 'TK013', 'VNPT Đà Nẵng', 'Tổng công ty', 'Viễn thông',
       '344 Hoàng Diệu, Hải Châu, Đà Nẵng', '0236445566', '44556677', 'vnpt.vn',
       'vnpt.png', 'Tập đoàn viễn thông hàng đầu Việt Nam', 'Chế độ nhà nước, môi trường ổn định'),

      ('DN008', 'TK014', 'Viettel Đà Nẵng', 'Tập đoàn', 'Viễn thông & Quốc phòng',
       '484 Hoàng Diệu, Hải Châu, Đà Nẵng', '0236778899', '77889900', 'viettel.com.vn',
       'viettel.png', 'Tập đoàn viễn thông và công nghệ quốc phòng', 'Phụ cấp quân đội, chế độ ưu đãi'),

      ('DN009', 'TK015', 'CodeGym Đà Nẵng', 'Công ty cổ phần', 'Đào tạo CNTT',
       '255 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', '0236112299', '11229933', 'codegym.vn',
       'codegym.png', 'Trung tâm đào tạo lập trình viên chuyên nghiệp', 'Môi trường học tập, phát triển bản thân');

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

-- Thêm dữ liệu vào bảng BAIDANG
INSERT INTO BAIDANG (maBaiDang, maDoanhNghiep, maDanhMuc, tieuDe, diaChi, yeuCau, moTa, denHan, quyenLoi, loai,
                     trangThai)
VALUES ('BD001', 'DN001', 'DM001', 'Thực tập sinh Frontend Developer', 'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng',
        'Có kiến thức về HTML, CSS, JavaScript; Hiểu biết về ReactJS là lợi thế; Có khả năng làm việc nhóm',
        'FPT Software tuyển dụng thực tập sinh Frontend Developer làm việc tại Đà Nẵng', '2025-06-30 23:59:59',
        'Được hỗ trợ ăn trưa; Có cơ hội được nhận vào làm việc chính thức; Được đào tạo chuyên môn', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD002', 'DN001', 'DM001', 'Thực tập sinh Backend Java', 'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng',
        'Có kiến thức nền tảng về Java, Spring Boot; Hiểu biết căn bản về SQL',
        'FPT Software tuyển dụng thực tập sinh Backend Java làm việc tại Đà Nẵng', '2025-06-30 23:59:59',
        'Được hỗ trợ ăn trưa; Có cơ hội được nhận vào làm việc chính thức; Được đào tạo chuyên môn', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD003', 'DN002', 'DM001', 'Thực tập sinh .NET Developer', '162 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng',
        'Có kiến thức về C#, .NET Framework; Hiểu biết cơ bản về SQL Server',
        'MISA tuyển dụng thực tập sinh .NET Developer làm việc tại văn phòng Đà Nẵng', '2025-05-30 23:59:59',
        'Hỗ trợ chi phí đi lại; Môi trường làm việc chuyên nghiệp; Có cơ hội trở thành nhân viên chính thức', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD004', 'DN003', 'DM001', 'Thực tập sinh Mobile Developer', '72 Lê Đình Lý, Hải Châu, Đà Nẵng',
        'Có kiến thức về lập trình Android hoặc iOS; Hiểu biết về React Native là lợi thế',
        'Axon tuyển dụng thực tập sinh Mobile Developer làm việc tại Đà Nẵng', '2025-05-15 23:59:59',
        'Được hỗ trợ phương tiện đi lại; Có cơ hội được nhận vào làm việc chính thức', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD005', 'DN004', 'DM001', 'Thực tập sinh QA/Tester', '295 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng',
        'Có kiến thức cơ bản về quy trình kiểm thử phần mềm; Hiểu biết về Selenium, Postman là lợi thế',
        'Enclave tuyển dụng thực tập sinh QA/Tester làm việc tại Đà Nẵng', '2025-06-15 23:59:59',
        'Được hỗ trợ ăn trưa; Có cơ hội được nhận vào làm việc chính thức', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD006', 'DN005', 'DM001', 'Thực tập sinh DevOps', '346 2/9, Hải Châu, Đà Nẵng',
        'Có kiến thức về Linux, Docker; Hiểu biết về CI/CD là lợi thế',
        'KMS Technology tuyển dụng thực tập sinh DevOps làm việc tại Đà Nẵng', '2025-06-20 23:59:59',
        'Được đào tạo bài bản; Có cơ hội được nhận vào làm việc chính thức', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD007', 'DN006', 'DM009', 'Thực tập sinh UI/UX Designer', '177 Trần Phú, Hải Châu, Đà Nẵng',
        'Có kiến thức về thiết kế giao diện người dùng, UX; Sử dụng thành thạo Figma, Adobe XD',
        'NeoLab tuyển dụng thực tập sinh UI/UX Designer làm việc tại Đà Nẵng', '2025-05-25 23:59:59',
        'Môi trường làm việc trẻ trung, năng động; Có cơ hội được nhận vào làm việc chính thức', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD008', 'DN007', 'DM001', 'Thực tập sinh Data Analyst', '344 Hoàng Diệu, Hải Châu, Đà Nẵng',
        'Có kiến thức về phân tích dữ liệu, SQL; Hiểu biết về Python, R là lợi thế',
        'VNPT Đà Nẵng tuyển dụng thực tập sinh Data Analyst làm việc tại văn phòng chính', '2025-06-10 23:59:59',
        'Được đào tạo về Big Data; Có cơ hội làm việc với dữ liệu lớn; Có cơ hội được nhận vào làm việc chính thức',
        'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD009', 'DN008', 'DM001', 'Thực tập sinh Network Engineer', '484 Hoàng Diệu, Hải Châu, Đà Nẵng',
        'Có kiến thức về mạng máy tính, CCNA; Hiểu biết về bảo mật mạng là lợi thế',
        'Viettel Đà Nẵng tuyển dụng thực tập sinh Network Engineer làm việc tại Đà Nẵng', '2025-05-20 23:59:59',
        'Được đào tạo chuyên sâu về mạng; Có cơ hội được nhận vào làm việc chính thức', 'TOAN_THOI_GIAN', 'CON_HAN'),
       ('BD010', 'DN009', 'DM001', 'Mentor Thực tập sinh bán thời gian', '255 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng',
        'Có kiến thức về lập trình; Kỹ năng giao tiếp tốt; Nhiệt tình, trách nhiệm',
        'CodeGym Đà Nẵng tuyển dụng Mentor thực tập sinh bán thời gian làm việc tại Đà Nẵng', '2025-06-05 23:59:59',
        'Lịch làm việc linh hoạt; Môi trường làm việc năng động; Có cơ hội được nhận vào làm việc chính thức', 'BAN_THOI_GIAN',
        'CON_HAN');

-- Thêm dữ liệu vào bảng KYNANG_BAIDANG
INSERT INTO KYNANG_BAIDANG (maKyNang, maBaiDang)
VALUES ('KN005', 'BD001'),
       ('KN006', 'BD001'),
       ('KN001', 'BD002'),
       ('KN008', 'BD002'),
       ('KN003', 'BD003'),
       ('KN008', 'BD003'),
       ('KN005', 'BD004'),
       ('KN006', 'BD004'),
       ('KN005', 'BD005'),
       ('KN008', 'BD005'),
       ('KN010', 'BD006'),
       ('KN008', 'BD006'),
       ('KN019', 'BD007'),
       ('KN020', 'BD007'),
       ('KN002', 'BD008'),
       ('KN008', 'BD008'),
       ('KN003', 'BD009'),
       ('KN010', 'BD009'),
       ('KN001', 'BD010'),
       ('KN002', 'BD010'),
       ('KN005', 'BD010');

-- Thêm dữ liệu vào bảng SINHVIEN_BAIDANG
INSERT INTO SINHVIEN_BAIDANG (maSVBD, maSinhVien, maBaiDang, soYeuLyLich, ketQua)
VALUES
    ('SVBD001', 'SV001', 'BD001', 'cv_sv002_bd002.pdf', 'THONG_QUA'),
    ('SVBD002', 'SV002', 'BD001', 'cv_sv002_bd001.pdf', 'TU_CHOI'),
    ('SVBD003', 'SV001', 'BD002', 'cv_sv001_bd002.pdf', 'THONG_QUA'),
    ('SVBD004', 'SV003', 'BD003', 'cv_sv003_bd003.pdf', 'THONG_QUA'),
    ('SVBD005', 'SV004', 'BD004', 'cv_sv004_bd004.pdf', 'TU_CHOI'),
    ('SVBD006', 'SV005', 'BD005', 'cv_sv005_bd005.pdf', 'THONG_QUA'),
    ('SVBD007', 'SV006', 'BD006', 'cv_sv006_bd006.pdf', 'THONG_QUA'),
    ('SVBD008', 'SV007', 'BD007', 'cv_sv007_bd007.pdf', 'TU_CHOI'),
    ('SVBD009', 'SV008', 'BD008', 'cv_sv008_bd008.pdf', 'THONG_QUA'),
    ('SVBD010', 'SV009', 'BD009', 'cv_sv009_bd009.pdf', 'THONG_QUA'),
    ('SVBD011', 'SV010', 'BD010', 'cv_sv010_bd010.pdf', 'THONG_QUA'),
    ('SVBD012', 'SV002', 'BD002', 'cv_sv002_bd002.pdf', 'DANG_CHO'),
    ('SVBD013', 'SV003', 'BD004', 'cv_sv003_bd004.pdf', 'DANG_CHO'),
    ('SVBD014', 'SV005', 'BD006', 'cv_sv005_bd006.pdf', 'DANG_CHO'),
    ('SVBD015', 'SV007', 'BD008', 'cv_sv007_bd008.pdf', 'DANG_CHO'),
    ('SVBD016', 'SV009', 'BD010', 'cv_sv009_bd010.pdf', 'DANG_CHO');


-- Thêm dữ liệu vào bảng LICHPHONGVAN
INSERT INTO LICHPHONGVAN (maLichPhongVan, maSinhVien, maDoanhNghiep, viTriPhongVan, ngayPhongVan, diaDiem, hinhThucPhongVan, trangThai, hanXacNhan)
VALUES
    ('LPV001', 'SV001', 'DN001', 'Developer Java', '2025-05-06 09:00:00', 'Phòng họp 1, FPT Software Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-14 23:59:59'),
    ('LPV002', 'SV001', 'DN001', 'Frontend Developer', '2025-05-06 14:00:00', 'Phòng họp 2, FPT Software Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-15 23:59:59'),
    ('LPV003', 'SV003', 'DN002', 'Backend Developer', '2025-05-07 10:00:00', 'Phòng họp A, MISA Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-16 23:59:59'),
    ('LPV004', 'SV005', 'DN004', 'Mobile Developer', '2025-04-18 09:30:00', 'Phòng họp 3, Enclave Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-17 23:59:59'),
    ('LPV005', 'SV006', 'DN005', 'DevOps Engineer', '2025-04-19 13:30:00', 'Phòng họp B, KMS Technology Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-18 23:59:59'),
    ('LPV006', 'SV008', 'DN007', 'Data Analyst', '2025-04-20 10:30:00', 'Phòng họp C, VNPT Đà Nẵng', 'TRUC_TIEP', 'TU_CHOI', '2025-04-19 23:59:59'),
    ('LPV007', 'SV009', 'DN008', 'System Administrator', '2025-05-21 09:00:00', 'Phòng họp D, Viettel Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-20 23:59:59'),
    ('LPV008', 'SV010', 'DN009', 'Developer C#', '2025-04-22 14:00:00', 'Phòng họp 4, CodeGym Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-21 23:59:59'),
    ('LPV009', 'SV002', 'DN003', 'Full Stack Developer', '2025-04-23 11:00:00', 'Phòng họp E, Axon Đà Nẵng', 'TRUC_TIEP', 'TU_CHOI', '2025-04-22 23:59:59'),
    ('LPV010', 'SV004', 'DN006', 'QA Engineer', '2025-04-24 15:30:00', 'Phòng họp F, NeoLab Đà Nẵng', 'TRUC_TIEP', 'DONG_Y', '2025-04-23 23:59:59'),
    ('LPV011', 'SV002', 'DN001', 'Java Developer', '2025-05-05 10:00:00', 'Phòng họp 1, FPT Software Đà Nẵng', 'TRUC_TIEP', 'DANG_CHO', '2025-05-03 23:59:59'),
    ('LPV012', 'SV003', 'DN003', 'Web Developer', '2025-05-07 14:30:00', 'Phòng họp chính, Axon Đà Nẵng', 'TRUC_TIEP', 'DANG_CHO', '2025-05-05 23:59:59'),
    ('LPV013', 'SV004', 'DN005', 'UI/UX Designer', '2025-05-10 09:00:00', 'Phòng họp A, KMS Technology Đà Nẵng', 'TRUC_TIEP', 'DANG_CHO', '2025-05-08 23:59:59'),
    ('LPV014', 'SV007', 'DN002', 'Database Administrator', '2025-05-12 11:00:00', 'Phòng họp B, MISA Đà Nẵng', 'TRUC_TIEP', 'DANG_CHO', '2025-05-10 23:59:59'),
    ('LPV015', 'SV010', 'DN004', 'Mobile Developer', '2025-05-15 15:00:00', 'Phòng họp 2, Enclave Đà Nẵng', 'TRUC_TIEP', 'DANG_CHO', '2025-05-13 23:59:59');

-- Thêm dữ liệu vào bảng LOIMOITHUCTAP
INSERT INTO LOIMOITHUCTAP (maLMTT, maSinhVien, maDoanhNghiep, viTriThucTap, tuNgay, denNgay, diaChi, loaiThucTap,
                           quyenLoi, trangThai, hanXacNhan)
VALUES ('LMTT001', 'SV001', 'DN001', 'Frontend Developer', '2025-05-01', '2025-08-01',
        'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Hỗ trợ ăn trưa, đào tạo chuyên môn, có cơ hội được nhận việc chính thức', 'CHAP_NHAN', '2025-04-20 23:59:59'),
       ('LMTT002', 'SV001', 'DN001', 'Backend Java Developer', '2025-05-15', '2025-08-15',
        'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Hỗ trợ ăn trưa, đào tạo chuyên môn, có cơ hội được nhận việc chính thức', 'TU_CHOI', '2025-04-25 23:59:59'),
       ('LMTT003', 'SV003', 'DN002', '.NET Developer', '2025-05-10', '2025-08-10',
        '162 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Hỗ trợ chi phí đi lại, môi trường chuyên nghiệp, cơ hội việc làm sau thực tập', 'CHAP_NHAN', '2025-04-30 23:59:59'),
       ('LMTT004', 'SV005', 'DN004', 'QA/Tester', '2025-05-20', '2025-08-20', '295 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng',
        'TOAN_THOI_GIAN', 'Hỗ trợ ăn trưa, được đào tạo về kiểm thử phần mềm', 'CHAP_NHAN', '2025-05-05 23:59:59'),
       ('LMTT005', 'SV006', 'DN005', 'DevOps Engineer', '2025-06-01', '2025-09-01', '346 2/9, Hải Châu, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Được đào tạo bài bản về CI/CD, cơ hội việc làm sau thực tập', 'CHAP_NHAN', '2025-05-15 23:59:59'),
       ('LMTT006', 'SV008', 'DN007', 'Data Analyst', '2025-05-25', '2025-08-25', '344 Hoàng Diệu, Hải Châu, Đà Nẵng',
        'TOAN_THOI_GIAN', 'Được đào tạo về Big Data, làm việc với dữ liệu lớn', 'TU_CHOI', '2025-05-10 23:59:59'),
       ('LMTT007', 'SV009', 'DN008', 'Network Engineer', '2025-06-05', '2025-09-05',
        '484 Hoàng Diệu, Hải Châu, Đà Nẵng', 'TOAN_THOI_GIAN', 'Được đào tạo chuyên sâu về mạng và bảo mật', 'CHAP_NHAN', '2025-05-20 23:59:59'),
       ('LMTT008', 'SV010', 'DN009', 'Mentor bán thời gian', '2025-05-15', '2025-10-15',
        '255 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', 'BAN_THOI_GIAN', 'Lịch làm việc linh hoạt, môi trường năng động', 'CHAP_NHAN', '2025-05-01 23:59:59'),
       ('LMTT009', 'SV002', 'DN003', 'Mobile Developer', '2025-06-10', '2025-09-10', '72 Lê Đình Lý, Hải Châu, Đà Nẵng',
        'TOAN_THOI_GIAN', 'Hỗ trợ phương tiện đi lại, đào tạo về React Native', 'TU_CHOI', '2025-05-25 23:59:59'),
       ('LMTT010', 'SV004', 'DN006', 'UI/UX Designer', '2025-06-15', '2025-09-15', '177 Trần Phú, Hải Châu, Đà Nẵng',
        'TOAN_THOI_GIAN', 'Môi trường làm việc sáng tạo, đào tạo về thiết kế UI/UX', 'CHAP_NHAN', '2025-05-30 23:59:59'),
       ('LMTT011', 'SV007', 'DN001', 'Python Developer', '2025-05-05', '2025-08-05',
        'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng', 'TOAN_THOI_GIAN', 'Hỗ trợ ăn trưa, đào tạo về Python, Django', 'CHAP_NHAN', '2025-04-20 23:59:59'),
       ('LMTT012', 'SV005', 'DN002', 'Business Analyst', '2025-07-01', '2025-10-01',
        '162 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', 'TOAN_THOI_GIAN', 'Hỗ trợ chi phí đi lại, đào tạo về phân tích yêu cầu phần mềm',
        'TU_CHOI', '2025-06-15 23:59:59'),
       ('LMTT013', 'SV002', 'DN001', 'Mobile App Developer', '2025-06-01', '2025-09-01',
        'Khu công nghệ FPT, Ngũ Hành Sơn, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Hỗ trợ ăn trưa, đào tạo chuyên môn về React Native, có cơ hội được nhận việc chính thức', 'DANG_CHO', '2025-05-20 23:59:59'),
       ('LMTT014', 'SV004', 'DN003', 'Blockchain Developer', '2025-06-15', '2025-09-15',
        '72 Lê Đình Lý, Hải Châu, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Hỗ trợ chi phí đi lại, đào tạo về công nghệ Blockchain, môi trường làm việc quốc tế', 'DANG_CHO', '2025-05-30 23:59:59'),
       ('LMTT015', 'SV006', 'DN004', 'Frontend Developer', '2025-06-01', '2025-09-01',
        '295 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Được đào tạo về ReactJS, Angular, môi trường làm việc năng động', 'DANG_CHO', '2025-05-20 23:59:59'),
       ('LMTT016', 'SV008', 'DN006', 'IoT Developer', '2025-06-15', '2025-09-15',
        '177 Trần Phú, Hải Châu, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Được đào tạo chuyên sâu về IoT, làm việc với các dự án thực tế', 'DANG_CHO', '2025-05-30 23:59:59'),
       ('LMTT017', 'SV010', 'DN002', 'ERP Developer', '2025-06-10', '2025-09-10',
        '162 Nguyễn Văn Linh, Thanh Khê, Đà Nẵng', 'TOAN_THOI_GIAN',
        'Hỗ trợ chi phí đi lại, đào tạo về hệ thống ERP, môi trường chuyên nghiệp', 'DANG_CHO', '2025-05-25 23:59:59');