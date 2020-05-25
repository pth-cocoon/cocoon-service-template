package icu.cocoon.system.util;

import icu.cocoon.util.TimeStampUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author xce
 * @date 2019/10/24 10:40
 */
@Slf4j
public class JwtUtil {

  private static String SECRET = "secret";

  private static long EXPIRATION_TIME = TimeStampUtil.getByWeek(1);

  private static String header = "Authorization";

  /**
   * 生成令牌
   *
   * @param userDetails 用户
   * @return 令牌
   */
  public static String generateToken(UserDetails userDetails) {
    Claims claims = new DefaultClaims().setSubject(userDetails.getUsername()).setIssuedAt(new Date());
    return generateToken(claims);
  }

  /**
   * 生成令牌
   *
   * @param username 用户
   * @return 令牌
   */
  public static String generateToken(String username) {
    Claims claims = new DefaultClaims().setSubject(username).setIssuedAt(new Date());
    return generateToken(claims);
  }

  /**
   * 从令牌中获取用户名
   *
   * @param token 令牌
   * @return 用户名
   */
  public static String getUsernameFromToken(String token) {
    String username = null;
    try {
      Claims claims = getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      log.error("token解析出错：{}", e.getMessage());
    }
    return username;
  }

  /**
   * 从数据声明生成令牌
   *
   * @param claims 数据声明
   * @return 令牌
   */
  private static String generateToken(Claims claims) {
    Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  /**
   * 从令牌中获取数据声明
   *
   * @param token 令牌
   * @return 数据声明
   */
  private static Claims getClaimsFromToken(String token) {
    Claims claims;
    claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    return claims;
  }

  /**
   * 刷新令牌
   *
   * @param token 原令牌
   * @return 新令牌
   */
  public String refreshToken(String token) {
    String refreshedToken;
    try {
      Claims claims = getClaimsFromToken(token);
      claims.setIssuedAt(new Date());
      refreshedToken = generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }

  /**
   * 验证令牌
   *
   * @param token       令牌
   * @param userDetails 用户
   * @return 是否有效
   */
  public static Boolean validateToken(String token, UserDetails userDetails) {
    return StringUtils.equals(userDetails.getUsername(), getUsernameFromToken(token));
  }


}
