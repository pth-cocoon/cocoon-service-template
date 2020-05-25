package icu.cocoon.security.service.impl;

import icu.cocoon.security.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class JwtServiceDefaultImpl implements JwtService {

  @Override
  public String getUsernameFromToken(String token) {
    log.error("默认返回admin，请自行实现JwtService");
    return "admin";
  }

  @Override
  public boolean validateToken(String token, UserDetails userDetails) {
    log.error("默认返回true，请自行实现JwtService");
    return true;
  }

  @Override
  public String generateToken(String username) {
    log.error("请自行实现JwtService");
    return "admin`s token";
  }
}
