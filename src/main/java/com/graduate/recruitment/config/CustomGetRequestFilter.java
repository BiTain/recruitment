package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.repository.SinhVienRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomGetRequestFilter extends OncePerRequestFilter {

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

            if (path.startsWith("/admin")) {
                if (authentication == null) {
                    response.sendRedirect("/");
                    return;
                }
            }

            if (path.startsWith("/doanh-nghiep")) {
                if (authentication == null) {
                    response.sendRedirect("/");
                    return;
                }
            }

            if (uri.startsWith("/sinh-vien/bai-da") || uri.startsWith("/sinh-vien/doanh-ngh")) {
                if (authentication != null) {
                    Object principalObj = authentication.getPrincipal();
                    if (principalObj instanceof CustomUserPrincipal principal) {
                        if (principal.getSinhVien() != null) {
                            SinhVien svInDB = sinhVienRepository.findById(principal.getSinhVien().getMaSinhVien()).get();
                            principal.setSinhVien(svInDB);
                        }
                    }
                }
            }

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
                }
            }

        }

        // Nếu hợp lệ thì tiếp tục
        filterChain.doFilter(request, response);
    }
}
