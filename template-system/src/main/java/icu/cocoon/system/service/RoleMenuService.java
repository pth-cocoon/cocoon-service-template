package icu.cocoon.system.service;


import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.Role;
import icu.cocoon.system.entity.RoleMenu;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface RoleMenuService extends BaseIService<RoleMenu> {

  List<RoleMenu> findByRoleId(Long roleId);

  boolean saveBatchByRole(Role role);

  int deleteByRoleId(Long roleId);
}
