package icu.cocoon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.cocoon.system.entity.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

  /**
   * 根据用户id查询菜单.
   *
   * @param userId 用户id
   * @return 菜单集合.
   */
  List<Menu> selectBatchByUserId(Long userId);

}
