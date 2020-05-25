package icu.cocoon.system.service;


import icu.cocoon.dao.base.BaseIService;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.entity.UserRole;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
public interface UserRoleService extends BaseIService<UserRole> {

  void saveBatchByUser(User entity);

  List<UserRole> findByUserId(Long userId);

  void removeByUserId(Long userId);
}
