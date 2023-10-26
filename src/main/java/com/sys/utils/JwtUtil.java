package com.sys.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil{

    /**
     * 默认有效期 30min
     */
    public static final Long JWT_TTL = 36000000L;

    /**
     * jwt令牌信息
     */
    public static final String JWT_KEY = "OASIS-TEAM-3-OASIS-TEAM-3";

    /**
     * 创建令牌
     * @param id 令牌唯一标识
     * @param subject 主题信息
     * @param ttlMillis 有效期
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis){

        // 指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 系统当前时间
        long nowMillis = System.currentTimeMillis();

        // 令牌颁发时间
        Date now = new Date(nowMillis);

        // 若传入的令牌有效期为null，则使用默认有效期
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        // 令牌过期时间
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 生成密钥
        SecretKey secretKey = generalKey();

        // 封装令牌信息
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id) //令牌唯一标识
                .setSubject(subject) // 主题，可以是JSON数据
                .setIssuer("OASIS-TEAM-3") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名以及密钥.参1：签名算法；参2：密钥（盐）
                .setExpiration(expDate);// 设置过期时间




        return jwtBuilder.compact();
/*         // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 构建jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder();
        // jwt颁发者
        jwtBuilder.setIssuer("酷奇");
        // 颁发日期
        jwtBuilder.setIssuedAt(new Date());
        // 主题信息
        jwtBuilder.setSubject("jwt令牌");
        // 签名。参1：签名算法；参2：密钥（盐）
        jwtBuilder.signWith(SignatureAlgorithm.HS256,"Tang-J-L");
        // 过期时间，一般为30分钟，当前系统时间 + 30min
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 1800000));
        // 自定义载荷信息，添加附加信息
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("company","CODER");
        userInfo.put("vip","coder-v");
        // 将自定义信息添加到令牌中
        jwtBuilder.addClaims(userInfo);

        // 生成jwtToken
        String token = jwtBuilder.compact(); */

    }

    /**
     * 生成加密 generalKey：对当前密钥进行再次Base64加密
     * @return
     */
    public static SecretKey generalKey(){
        byte[] encodeKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
        return key;
    }


    /**
     * 解析令牌
     */
    public static Claims parseJWT(String jwt){
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        /**
         * setSigningKey()：解析密钥
         * parseClaimsJws()：解析token
         * getBody()：获取解析后的数据
         * toString()：讲解析后的令牌转换为String得到最终的令牌
         */
        // Claims jwtToken = Jwts.parser().setSigningKey("Tang-J-L").parseClaimsJws().getBody().toString();
    }
}


