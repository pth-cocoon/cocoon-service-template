package icu.cocoon.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.mapper.UserMapper;
import icu.cocoon.system.service.UserRoleService;
import icu.cocoon.system.service.UserService;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends BaseService<UserMapper, User> implements UserService {

  @Resource
  private UserRoleService userRoleService;

  @Resource
  private UserDetailsService userDetailsService;

  @Override
  public int create(User entity) {
    int result = super.create(entity);
    userRoleService.saveBatchByUser(entity);
    return result;
  }

  @Override
  public int update(User entity) {
    userRoleService.removeByUserId(entity.getId());
    userRoleService.saveBatchByUser(entity);
    return super.update(entity);
  }

  @Cacheable
  @Override
  public User getByUsername(String username) {
    return getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
  }

  @Cacheable
  @Override
  public User getByPhone(String phone) {
    return getOne(new QueryWrapper<User>().lambda().eq(User::getPhone, phone));
  }

  @Override
  public UserDetails getUserDetails(User user) {
    return userDetailsService.loadUserByUsername(user.getUsername());
  }

}
