package com.littlefisher.base.util;

import com.littlefisher.base.constants.JwtConstants;
import com.littlefisher.base.properties.JwtProperties;
import com.littlefisher.base.vo.SysRoleVo;
import com.littlefisher.base.vo.SysUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Jwt Util
 */
@Component
public class JwtTokenUtil {

//    @Autowired
//    private SecurityProperties securityProperties;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key从token中
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     */
    public String generateToken(Map<String, Object> claims, String randomKey) {
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, (String) claims.get("username"));
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(jwtProperties.getSecret());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 生成token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + jwtProperties.getExpiration() * 1000);

        return Jwts.builder()
                .setHeader(setJwtHeader())
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS512, generalKey())
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
    }

    private Map<String, Object> setJwtHeader() {
        Map<String, Object> map = new HashMap<>();
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        return map;
    }

    /**
     * 获取混淆MD5签名用的随机字符串
     */
    public String getRandomKey() {
        return getRandomString(6);
    }

    /**
     * 获取随机位数的字符串
     */
    public static String getRandomString(int length) {
        final String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 刷新token
     *
     * @param token：token
     * @return
     */
    public String refreshToken(String token, String randomKey) {
        String refreshedToken;
        try {
            final Claims claims = getClaimFromToken(token);
            refreshedToken = generateToken(new HashMap<>(), randomKey);
            //todo: 换个更好的方案
            Map<String, Object> temp = new HashMap<>();
            temp.put(JwtConstants.USER_ID, claims.get(JwtConstants.USER_ID));
            temp.put(JwtConstants.USER_NAME, claims.get(JwtConstants.USER_NAME));
            temp.put(JwtConstants.AUTHORITIES, claims.get(JwtConstants.AUTHORITIES));

            refreshedToken = generateToken(temp, randomKey);
        } catch (Exception e1) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public List<String> getUserRoles(HttpServletRequest request){
        String token = getTokenFromRequest(request);
        if (token == null) {
            return null;
        }
        Set<String> userRoles= new HashSet<String>();
        List<Map<String,Object>> roleVos = (List<Map<String, Object>>) getClaimFromToken(token).get(JwtConstants.AUTHORITIES);
        roleVos.forEach(roleVo->{
            userRoles.add((String) roleVo.get("roleCode"));
        });
        return new ArrayList<>(userRoles) ;
    }

    /**
     * 从符合条件的请求中取出token
     * @param request
     * @return
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String authHeader = beValidJwtRequest(request);
        if (authHeader==null) {
            return null;
        }
        return authHeader.substring(JwtConstants.AUTH_HEADER_START.length());
    }


    /**
     * 检测 request 是否符合  Authorization：bearer XXXXXXXX...
     * 符合返回 request header,不符合返回null
     * @param request
     * @return
     */
    public String beValidJwtRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtConstants.AUTH_HEADER);
        if((StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtConstants.AUTH_HEADER_START))){
            return null;
        }
        return authHeader;
    }


//    /**
//     * 设置jwt用户信息
//     * @param claims
//     * @return
//     */
//    public Map<String, Object> userInfoClaim(Map<String, Object>  claims){
//        Map<String, Object> map = new HashMap<>();
//        map.put("username", claims.get("username"));
//        map.put("authorities", claims.get("authorities"));
//        return map;
//    }
//
//    /**
//     * 设置jwt用户信息
//     * @param authentication
//     * @return
//     */
//    public Map<String, Object> userInfoClaim(Authentication authentication){
//        Map<String, Object> claims = new HashMap<>();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        claims.put("username", userDetails.getUsername());
//        claims.put("authorities", userDetails.getAuthorities());
//        return claims;
//    }
}