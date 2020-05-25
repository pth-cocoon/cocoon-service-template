package icu.cocoon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.cocoon.system.entity.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

  /**
   * 根据用户id查询权限.
   *
   * @param userId 用户id
   * @return 权限集合.
   */
  List<Permission> selectBatchByUserId(Long userId);

  List<Permission> selectBatchByRoleName(String roleName);
}
