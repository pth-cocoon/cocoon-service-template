package icu.cocoon.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.Role;
import icu.cocoon.system.entity.RolePermission;
import icu.cocoon.system.mapper.RolePermissionMapper;
import icu.cocoon.system.service.RolePermissionService;
import java.util.List;
import java.util.stream.Collectors;
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
public class RolePermissionServiceImpl extends BaseService<RolePermissionMapper, RolePermission> implements
    RolePermissionService {

  @Override
  public int deleteByRoleId(Long roleId) {
    return remove(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getRoleId, roleId));
  }

  @Override
  public boolean saveBatchByRole(Role role) {
    List<RolePermission> entityList = role.getPermissionIds().stream()
        .map(permissionId -> new RolePermission(role.getId(), permissionId)).collect(Collectors.toList());
    return create(entityList);
  }

  @Override
  public List<RolePermission> findByRoleId(Long roleId) {
    return list(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getRoleId, roleId));
  }

  @Override
  public List<RolePermission> findByPermissionId(Long permissionId) {
    return list(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getPermissionId, permissionId));
  }

  @Override
  public List<Long> findPermissionIdsByRoleId(Long roleId) {
    List<RolePermission> rolePermissions = findByRoleId(roleId);
    return rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
  }


}
