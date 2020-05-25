package icu.cocoon.system.service;

import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.Role;
import icu.cocoon.system.entity.RolePermission;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface RolePermissionService extends BaseIService<RolePermission> {

  int deleteByRoleId(Long roleId);


  boolean saveBatchByRole(Role role);

  List<RolePermission> findByRoleId(Long roleId);

  List<RolePermission> findByPermissionId(Long permissionId);

  List<Long> findPermissionIdsByRoleId(Long roleId);
}
