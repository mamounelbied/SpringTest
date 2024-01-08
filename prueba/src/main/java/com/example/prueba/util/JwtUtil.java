package com.example.prueba.util;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "yoursecretkeyyoursecretkeyyoursecretkeyyoursecretkeyyoursecretkeyyoursecretkeyyoursecretkey";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}