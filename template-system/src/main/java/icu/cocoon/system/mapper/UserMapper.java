package icu.cocoon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.cocoon.system.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

}
