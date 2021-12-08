package com.xu.server.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt Token工具类
 */
@Component
public class JwtTokenUtils {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     * @param userDetails springsecurty中的默认用户
     * @return 返回token
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token获得荷载
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成token
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims) {
        String token = null;
        try{
            token = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512,secret)
                    .compact();
        }catch (ExpiredJwtException e){
            e.getClaims();
        }
        return token;

    }

    /**
     * 生成token过期时间
     * @return
     */
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration * 1000);
    }

    /**
     * 根据token获得过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String name = getUserNameFromToken(token);
        return name.equals(userDetails.getUsername()) && !isTokenExpiration(token);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    public boolean isTokenExpiration(String token) {
        Date date = getExpirationDateFromToken(token);
        return date.before(new Date());

    }

    /**
     * 判断token是否可以刷新
     * @param token
     * @return
     */
    public boolean isRefreshToken(String token){
        return !isTokenExpiration(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }


}
