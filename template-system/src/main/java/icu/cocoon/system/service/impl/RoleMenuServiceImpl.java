package icu.cocoon.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.Role;
import icu.cocoon.system.entity.RoleMenu;
import icu.cocoon.system.mapper.RoleMenuMapper;
import icu.cocoon.system.service.RoleMenuService;
import java.util.List;
import java.util.stream.Collectors;
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
public class RoleMenuServiceImpl extends BaseService<RoleMenuMapper, RoleMenu> implements RoleMenuService {

  @Override
  public List<RoleMenu> findByRoleId(Long roleId) {
    return list(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId));
  }


  @Override
  public boolean saveBatchByRole(Role role) {
    if (CollectionUtils.isEmpty(role.getMenuIds())) {
      return false;
    }
    List<RoleMenu> entityList = role.getMenuIds().stream()
        .map(permissionId -> new RoleMenu(role.getId(), permissionId)).collect(Collectors.toList());
    return create(entityList);
  }

  @Override
  public int deleteByRoleId(Long roleId) {
    return remove(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId));
  }
}
