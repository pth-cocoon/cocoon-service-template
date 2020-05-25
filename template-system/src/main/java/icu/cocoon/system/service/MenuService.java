package icu.cocoon.system.service;


import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.Menu;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface MenuService extends BaseIService<Menu> {

  List<Menu> findByRoleId(Long roleId);

  List<Menu> findByUserId(Long roleId);
}
