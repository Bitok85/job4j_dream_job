package ru.job4j.dream.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("loginPage") || uri.endsWith("login")) {
            filterChain.doFilter(req, res);
            return;
        }
        if (uri.endsWith("regUser") || uri.endsWith("registration")) {
            filterChain.doFilter(req, res);
            return;
        }
        if (uri.endsWith("index")) {
            filterChain.doFilter(req, res);
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        filterChain.doFilter(req, res);
    }
}
