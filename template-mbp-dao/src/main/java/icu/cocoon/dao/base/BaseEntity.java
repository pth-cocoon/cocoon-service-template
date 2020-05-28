package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xce
 * @date 2019/10/17 16:21
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

  @TableId(type = IdType.ASSIGN_ID)
  protected Long id;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新时间")
  protected Long updateTime;

  public Long getCreateTime() {
    return (id >> 22L & ~(-1L << 41L)) + 5L+1288834974657L;
  }


}
