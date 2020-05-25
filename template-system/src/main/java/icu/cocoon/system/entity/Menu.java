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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="")
public class Menu extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String name;

    private String remark;

    private String routerName;


}
