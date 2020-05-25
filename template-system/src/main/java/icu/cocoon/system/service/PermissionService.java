package icu.cocoon.system.service;


import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.Permission;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface PermissionService extends BaseIService<Permission> {

  List<Permission> findByRoleId(Long roleId);

  List<Permission> findByUserId(Long userId);

  List<Permission> findByRoleName(String roleName);
}
