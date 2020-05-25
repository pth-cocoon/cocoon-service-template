package icu.cocoon.system.service.impl;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.Permission;
import icu.cocoon.system.mapper.PermissionMapper;
import icu.cocoon.system.service.PermissionService;
import icu.cocoon.system.service.RolePermissionService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "permission")
public class PermissionServiceImpl extends BaseService<PermissionMapper, Permission> implements PermissionService {

  @Resource
  private RolePermissionService rolePermissionService;

  @Override
  public List<Permission> findByRoleId(Long roleId) {
    List<Long> ids = rolePermissionService.findPermissionIdsByRoleId(roleId);
    if (CollectionUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }
    return listByIds(ids);
  }

  @Override
  public List<Permission> findByUserId(Long userId) {
    return getMapper().selectBatchByUserId(userId);
  }

  @Cacheable
  @Override
  public List<Permission> findByRoleName(String roleName) {
    return getMapper().selectBatchByRoleName(roleName);
  }
}
