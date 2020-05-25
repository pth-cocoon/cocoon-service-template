package icu.cocoon.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityUserService extends UserDetailsService {

  /**
   * 根据 手机号 查找用户
   *
   * @param phone 手机号
   * @return SecurityUser
   */
  UserDetails getByPhone(String phone);

  /**
   * 获取当前用户.
   *
   * @return SecurityUser
   */
  UserDetails getCurrentUserUserDetails();
}
