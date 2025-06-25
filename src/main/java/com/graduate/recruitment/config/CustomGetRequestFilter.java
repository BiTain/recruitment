package com.graduate.recruitment.config;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        if(uri.startsWith("/sinh-vien")) {

        }
        

        // Chỉ kiểm tra các request GET
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            String path = request.getRequestURI();
            String query = request.getQueryString();

            if (path.startsWith("/sinh-vien") || "/".equals(path)) {
                var securityContext = SecurityContextHolder.getContext();
                var authentication = securityContext.getAuthentication();

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
                }
            }
        }

        // Nếu hợp lệ thì tiếp tục
        filterChain.doFilter(request, response);
    }
}
