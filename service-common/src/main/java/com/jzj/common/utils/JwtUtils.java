package com.jzj.common.utils;

import com.jzj.common.utils.result.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p>
 * Jwt工具类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class JwtUtils {

    private final static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ukc8BDbRzgUDaY6pZFfWus2jZWLPHO";

    public static final String USERID = "userId";
    public static final String USERNAME = "username";

    public static String getJwtToken(String id,String username){

        String JwtToken = Jwts.builder()
                //头部信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //时间设置
                .setSubject("AUTH-USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //主体信息
                .claim(USERID,id)
                .claim(USERNAME,username)
                //签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getSubject(String token,String key){
        try {
            if(!checkToken(token)) throw new BusinessException("token无效");
            if (StringUtils.isEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get(key);
        } catch (Exception e) {
            return null;
        }
    }
}
