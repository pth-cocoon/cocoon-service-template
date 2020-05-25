package icu.cocoon.system.service;

import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface UserService extends BaseIService<User> {

  /**
   * 根据 用户名 获取用户.
   * @param username 用户名.
   * @return 用户对象.
   */
  User getByUsername(String username);

  /**
   * 根据手机号获取用户.
   * @param phone 用户手机号.
   * @return 用户对象.
   */
  User getByPhone(String phone);

  /**
   * 转换为UserDetails对象
   * @param user 原始用户对象
   * @return UserDetails
   */
  UserDetails getUserDetails(User user);
}
