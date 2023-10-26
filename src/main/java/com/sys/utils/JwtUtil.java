package com.sys.utils;

import com.sys.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
// ... (other imports)

public class JwtUtil {
    static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public static String createToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getUid())
                .claim("uid", user.getUid())
                .claim("nickname", user.getNickname())
                .claim("head", user.getHead())
                .claim("registerDate", user.getRegisterDate().toString())
                .claim("lastLoginDate", user.getLastLoginDate().toString())
                .claim("loginCount", user.getLoginCount())
                .claim("major", user.getMajor())
                .claim("identity", user.getIdentity())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }


    public static User verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();
            user.setUid((String) claims.get("uid"));
            user.setNickname((String) claims.get("nickname"));
            user.setHead((String) claims.get("head"));
            user.setRegisterDate(LocalDateTime.parse((String) claims.get("registerDate")));
            user.setLastLoginDate(LocalDateTime.parse((String) claims.get("lastLoginDate")));
            user.setLoginCount((Integer) claims.get("loginCount"));
            user.setMajor((String) claims.get("major"));
            user.setIdentity((String) claims.get("identity"));

            return user;
        } catch (JwtException ex) {
            // Log the error or handle it as appropriate
            return null;
        }
    }
}
