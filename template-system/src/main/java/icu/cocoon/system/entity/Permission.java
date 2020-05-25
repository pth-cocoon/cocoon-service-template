package icu.cocoon.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.cocoon.dao.base.BaseEntity;
import io.swagger.annotations.ApiModel;
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
@TableName( "sys_permission" )
@ApiModel( value = "Permission对象", description = "" )
public class Permission extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean deleteMethod;

  private Boolean getMethod;

  private String name;

  private String parentId;

  private Boolean postMethod;

  private Boolean putMethod;

  private String remark;

  private String uri;

}
