package icu.cocoon.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.cocoon.dao.base.BaseService;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.entity.UserRole;
import icu.cocoon.system.mapper.UserRoleMapper;
import icu.cocoon.system.service.UserRoleService;
import java.util.ArrayList;
import java.util.Collections;
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
public class UserRoleServiceImpl extends BaseService<UserRoleMapper, UserRole> implements UserRoleService {

  @Override
  public void saveBatchByUser(User entity) {
    if (CollectionUtils.isEmpty(entity.getRoleIds())) {
      Collections.sort(new ArrayList<Integer>());
      return;
    }
    List<UserRole> userRoleList = entity.getRoleIds().stream()
        .map(roleId -> new UserRole(entity.getId(), roleId)).collect(Collectors.toList());
    create(userRoleList);
  }

  @Override
  public List<UserRole> findByUserId(Long userId) {
    return list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId));
  }

  @Override
  public void removeByUserId(Long userId) {
    remove(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId));
  }
}
