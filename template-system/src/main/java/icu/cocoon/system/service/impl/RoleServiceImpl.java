package icu.cocoon.system.service.impl;


import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.Role;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.entity.UserRole;
import icu.cocoon.system.mapper.RoleMapper;
import icu.cocoon.system.service.RoleMenuService;
import icu.cocoon.system.service.RolePermissionService;
import icu.cocoon.system.service.RoleService;
import icu.cocoon.system.service.UserRoleService;
import icu.cocoon.system.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@Service
@CacheConfig( cacheNames = "role" )
public class RoleServiceImpl extends BaseService<RoleMapper, Role> implements RoleService {


  @Resource
  private RolePermissionService rolePermissionService;

  @Resource
  private RoleMenuService roleMenuService;

  @Resource
  private UserRoleService userRoleService;

  @Resource
  private UserService userService;


  @Override
  public int create(Role entity) {
    int result = super.create(entity);
    rolePermissionService.saveBatchByRole(entity);
    roleMenuService.saveBatchByRole(entity);
    return result;
  }

  @Override
  public int removeById(Serializable id) {
    rolePermissionService.deleteByRoleId((Long) id);
    roleMenuService.deleteByRoleId((Long) id);
    return super.removeById(id);
  }

  @Override
  public void removeByIds(Collection<Long> idList) {
    idList.forEach(this::removeById);
    CollectionUtils.isEmpty(idList);
  }

  @Override
  public int update(Role entity) {
    rolePermissionService.deleteByRoleId(entity.getId());
    rolePermissionService.saveBatchByRole(entity);

    roleMenuService.deleteByRoleId(entity.getId());
    roleMenuService.saveBatchByRole(entity);
    return super.update(entity);
  }

  @Cacheable
  @Override
  public List<Role> findByUsername(String username) {
    User user = userService.getByUsername(username);
    if (user == null) {
      return new ArrayList<>();
    }
    return findByUserId(user.getId());
  }

  @Cacheable
  @Override
  public List<Role> findByUserId(Long userId) {
    List<Long> roleIds = userRoleService.findByUserId(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());
    if (CollectionUtils.isEmpty(roleIds)) {
      return new ArrayList<>();
    }
    return listByIds(roleIds);
  }
}
