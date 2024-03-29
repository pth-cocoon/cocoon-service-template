package icu.cocoon.dao.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import icu.cocoon.dao.support.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xce
 * @date 2019/10/17 16:21
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

  @JsonSerialize(using= ToStringSerializer.class)
  @NotNull(message = "ID不允许为空",groups = ValidationGroup.Edit.class)
  protected Long id;

  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "更新时间")
  protected Long updateTime;

  /**
   * 创建时间默认使用MybatisPlus的雪花算法，反推创建时间。
   * @return long
   */
  public Long getCreateTime() {
    if (id==null){
      return 0L;
    }
    return (id >> 22L & ~(-1L << 41L)) + 5L+1288834974657L;
  }


}
