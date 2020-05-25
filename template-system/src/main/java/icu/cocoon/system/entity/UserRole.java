package icu.cocoon.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.cocoon.dao.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode( callSuper = true )
@Accessors( chain = true )
@TableName( "sys_user_role" )
@ApiModel( value = "UserRole对象", description = "" )
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {


  private Long userId;
  private Long roleId;


}
