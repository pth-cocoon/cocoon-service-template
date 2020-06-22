package icu.cocoon.core.resp;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Cocoon
 * @date 2020/4/9  12:05
 * @node 分页信息
 */
public interface IPageInfo<T> extends IResp<T> {

  @ApiModelProperty("当前页码")
  long getCurrent();

  @ApiModelProperty("每页数量")
  long getSize();

  @ApiModelProperty("总数量")
  long getTotal();
}
