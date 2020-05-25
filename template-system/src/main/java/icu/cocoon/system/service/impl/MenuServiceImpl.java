package icu.cocoon.system.service.impl;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.Menu;
import icu.cocoon.system.entity.RoleMenu;
import icu.cocoon.system.mapper.MenuMapper;
import icu.cocoon.system.service.MenuService;
import icu.cocoon.system.service.RoleMenuService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
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
public class MenuServiceImpl extends BaseService<MenuMapper, Menu> implements MenuService {

  @Resource
  private RoleMenuService roleMenuService;

  @Override
  public List<Menu> findByRoleId(Long roleId) {
    List<Long> ids = roleMenuService.findByRoleId(roleId).stream().map(RoleMenu::getMenuId)
        .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }
    return listByIds(ids);
  }

  @Override
  public List<Menu> findByUserId(Long userId) {
    return getMapper().selectBatchByUserId(userId);
  }
}
