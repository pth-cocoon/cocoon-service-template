package icu.cocoon.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.cocoon.dao.base.BaseEntity;
import icu.cocoon.system.handler.PasswordHandler;
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
@TableName( "sys_user" )
@ApiModel( value = "User对象", description = "" )
public class User extends BaseEntity {


  @JsonIgnore
  @TableField( typeHandler = PasswordHandler.class )
  private String password;

  private String phone;

  private String username;

  private String avatarUrl;

  @TableField(exist = false)
  private List<Long> roleIds;


}
