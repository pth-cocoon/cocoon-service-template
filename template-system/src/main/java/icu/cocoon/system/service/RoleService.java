package icu.cocoon.system.service;


import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.Role;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface RoleService extends BaseIService<Role> {


  List<Role> findByUsername(String username);

  List<Role> findByUserId(Long userId);
}
