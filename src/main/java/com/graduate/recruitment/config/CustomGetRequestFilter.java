package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiSinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.SinhVienRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Component
public class CustomGetRequestFilter extends OncePerRequestFilter {

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        // Chỉ kiểm tra các request GET
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            String path = request.getRequestURI();
            String query = request.getQueryString();

            var securityContext = SecurityContextHolder.getContext();
            var authentication = securityContext.getAuthentication();

            // chan truy cap trang admin
            if (path.startsWith("/admin") && !path.equals("/admin/dang-nhap")) {
                if (authentication == null) {
                    response.sendRedirect("/");
                    return;
                } else {
                    Object principalObj = authentication.getPrincipal();
                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (!principal.getTaiKhoan().getEmail().startsWith("admin@admin")) {
                            response.sendRedirect("/");
                            return;
                        }
                    }
                }
            }

            // chan truy cap trang doanh-nghiep
            if (path.startsWith("/doanh-nghiep")
                    && !path.equals("/doanh-nghiep/dang-nhap")
                    && !path.equals("/doanh-nghiep/dang-ky")) {
                if (authentication == null) {
                    response.sendRedirect("/");
                    return;
                }
                else {
                    Object principalObj = authentication.getPrincipal();
                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getDoanhNghiep() == null) {
                            response.sendRedirect("/");
                            return;
                        }
                    }
                }
            }

            // chan truy cap trang nha-truong
            if (path.startsWith("/nha-truong")
                    && !path.equals("/nha-truong/dang-nhap")
                    && !path.equals("/nha-truong/dang-ky")) {
                if (authentication == null) {
                    response.sendRedirect("/");
                    return;
                }
                else {
                    Object principalObj = authentication.getPrincipal();
                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getNhaTruong() == null) {
                            response.sendRedirect("/");
                            return;
                        }
                    }
                }
            }

            // kiem tra check trang thai button ung tuyen
            if (uri.startsWith("/sinh-vien/bai-da") || uri.startsWith("/sinh-vien/doanh-ngh")) {
                if (authentication != null) {
                    Object principalObj = authentication.getPrincipal();
                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getSinhVien() != null) {
                            SinhVien svInDB = sinhVienRepository.findById(principal.getSinhVien().getMaSinhVien()).get();
                            CustomUserPrincipal newPri = new CustomUserPrincipal(principal.getTaiKhoan(), svInDB, Collections.emptyList());
                            UsernamePasswordAuthenticationToken newAu =
                                    new UsernamePasswordAuthenticationToken(newPri, null, new ArrayList<>());
                            SecurityContextHolder.getContext().setAuthentication(newAu);
                        }
                    }
                }
            }

            // kiem tra truy cap trang sinh-vien(chi khi la sinh vien)
            // neu khong phai sinh vien thi chuyen ve trang cua role do
            if (path.startsWith("/sinh-vien") || "/".equals(path)) {
                if (authentication != null) {
                    Object principalObj = authentication.getPrincipal();

                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getDoanhNghiep() != null) {
                            response.sendRedirect("/doanh-nghiep");
                            return;
                        }
                    }

                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getNhaTruong() != null) {
                            response.sendRedirect("/nha-truong/thong-tin");
                            return;
                        }
                    }

                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getTaiKhoan().getEmail().startsWith("admin@admin")) {
                            response.sendRedirect("/admin/bai-dang");
                            return;
                        }
                    }

                    // neu tai khoan bi khoa thi phai cho logout
                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getSinhVien() != null) {
                            SinhVien sinhVienInDB = sinhVienRepository.findById(principal.getSinhVien().getMaSinhVien()).get();
                            if (sinhVienInDB.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.BI_KHOA)) {
                                request.getSession().invalidate();
                                response.sendRedirect("/");
                                return;
                            }

                        }
                    }
                }
            }

        }

        // Nếu hợp lệ thì tiếp tục
        filterChain.doFilter(request, response);
    }
}
