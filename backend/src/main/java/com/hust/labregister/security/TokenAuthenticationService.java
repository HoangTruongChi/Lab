package com.hust.labregister.security;

import com.hust.labregister.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";


    static void addAuthentication(HttpServletResponse res, String username, Collection<? extends GrantedAuthority> authorities) {
        SimpleGrantedAuthority sga = (SimpleGrantedAuthority) authorities.iterator().next();
        String role = sga.getAuthority();
        String JWT = Jwts.builder()
                .setSubject(username)
                .setIssuer(role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            token = token.replace(TOKEN_PREFIX, "");
            // parse the token.
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody();
                String user = claims.getSubject();
                String role = claims.getIssuer();

                return user != null ?
                        new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority(role))) :
                        null;
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    static User getAuthentication(String token) {
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            String role = claims.getIssuer();

            User user = new User();
            user.setEmail(username);
            user.setRole(role);
            return user;
        }
        return null;
    }
}
