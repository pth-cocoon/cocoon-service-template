package icu.cocoon.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import icu.cocoon.dao.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@TableName( "sys_role" )
@ApiModel( value = "Role对象", description = "" )
public class Role extends BaseEntity {

  private String name;

  private String nameZh;

  @TableField( exist = false )
  private List<Long> permissionIds;

  @TableField( exist = false )
  private List<Long> menuIds;

}
