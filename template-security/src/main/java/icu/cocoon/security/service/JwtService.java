package icu.cocoon.security.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 默认使用Jwt作实现，封装了部分Jwt相关动作
 */
public interface JwtService {


  /**
   * 通过token获取用户名.
   *
   * @param token 通常为jwt
   * @return username
   */
  String getUsernameFromToken(String token);

  /**
   * 通过token验证用户
   *
   * @param token       通常为jwt
   * @param userDetails security 封装的 用户详情接口.
   * @return true = 合法；false = 不合法
   */
  boolean validateToken(String token, UserDetails userDetails);

  String generateToken(String username);
}
