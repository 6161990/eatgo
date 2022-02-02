package kr.co.yeoeulsim.eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private  Key key;
    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); // 256 / 8bit = 32, 32글자 이상이어야함
    }

    public String createToken(Long userId, String userName) {

        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token) //jws : 사인이 포함된 jwt
                .getBody();
    }
}
