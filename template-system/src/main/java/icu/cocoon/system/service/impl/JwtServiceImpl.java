package icu.cocoon.system.service.impl;

import icu.cocoon.security.service.JwtService;
import icu.cocoon.system.util.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  @Override
  public String getUsernameFromToken(String token) {
    return JwtUtil.getUsernameFromToken(token);
  }

  @Override
  public boolean validateToken(String token, UserDetails userDetails) {
    return JwtUtil.validateToken(token, userDetails);
  }

  @Override
  public String generateToken(String username) {
    return JwtUtil.generateToken(username);
  }
}
