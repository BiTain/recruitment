package com.graduate.recruitment.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    private final Path rootDir = Paths.get("uploads");
    private final Path resumeDir;
    private final Path imageDir;

    private static final List<String> RESUME_EXTENSIONS = Arrays.asList("pdf", "docx", "doc");

    public FileService() {
        try {
            // Tạo thư mục gốc nếu chưa tồn tại
            if (!Files.exists(rootDir)) {
                Files.createDirectories(rootDir);
            }

            // Khởi tạo thư mục con
            resumeDir = rootDir.resolve("resume");
            imageDir = rootDir.resolve("image");

            // Tạo các thư mục con nếu chưa tồn tại
            if (!Files.exists(resumeDir)) {
                Files.createDirectories(resumeDir);
            }

            if (!Files.exists(imageDir)) {
                Files.createDirectories(imageDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục uploads hoặc thư mục con", e);
        }
    }

    public String store(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
        }

        // Tạo tên file với timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName = timestamp + "." + fileExtension;  // Ví dụ: 20250304123045123.png

        // Xác định thư mục lưu trữ dựa trên phần mở rộng
        Path targetDir;
        if (isResumeFile(fileExtension)) {
            targetDir = resumeDir;
        } else {
            targetDir = imageDir;
        }

        // Đường dẫn đầy đủ để lưu file
        Path targetLocation = targetDir.resolve(newFileName);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file", e);
        }

        return newFileName;
    }

    public Resource loadFile(String filename) {
        try {
            // Xác định đường dẫn đầy đủ của file
            String fileExtension = "";
            if (filename != null && filename.contains(".")) {
                fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            }

            // Kiểm tra xem file thuộc thư mục nào
            Path filePath;
            if (isResumeFile(fileExtension)) {
                filePath = resumeDir.resolve(filename);
            } else {
                filePath = imageDir.resolve(filename);
            }

            // Nếu không tìm thấy ở thư mục dự đoán, thử tìm ở thư mục còn lại
            if (!Files.exists(filePath)) {
                if (isResumeFile(fileExtension)) {
                    filePath = imageDir.resolve(filename);
                } else {
                    filePath = resumeDir.resolve(filename);
                }
            }

            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File không tồn tại: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi khi tải file", e);
        }
    }

    public boolean delete(String filename) {
        try {
            String fileExtension = "";
            if (filename != null && filename.contains(".")) {
                fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            }

            // Kiểm tra xem file thuộc thư mục nào
            Path filePath;
            if (isResumeFile(fileExtension)) {
                filePath = resumeDir.resolve(filename);
            } else {
                filePath = imageDir.resolve(filename);
            }

            // Nếu không tìm thấy ở thư mục dự đoán, thử tìm ở thư mục còn lại
            if (!Files.exists(filePath)) {
                if (isResumeFile(fileExtension)) {
                    filePath = imageDir.resolve(filename);
                } else {
                    filePath = resumeDir.resolve(filename);
                }
            }

            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi xóa file: " + filename, e);
        }
    }

    private boolean isResumeFile(String fileExtension) {
        return RESUME_EXTENSIONS.contains(fileExtension.toLowerCase());
    }
}
