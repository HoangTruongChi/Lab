package com.hust.labregister.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final static List whiteList = Arrays.asList("/auth/signIn", "/auth/signUp");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader("123");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");
//        response.setHeader("X-Content-Type-Options", "nosniff");

        String contextPath = request.getServletPath();

        if(whiteList.contains(contextPath)){
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = TokenAuthenticationService
                .getAuthentication(request);

        if(authentication != null) {
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            filterChain.doFilter(request, response);
            return;
        }else{
            response.setStatus(401);
//            response.setContentType("text/plain");    // Not required
//            PrintWriter out = response.getWriter();
//            out.write("POST something back to the GET Request");
//            out.flush();
//            out.close();
        }
    }

}