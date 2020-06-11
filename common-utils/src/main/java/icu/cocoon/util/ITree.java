package icu.cocoon.util;

/**
 * @author xce
 * @date 2020/4/1  14:41
 */
public interface ITree {

  /**
   * 主键
   */
  Long getId();

  /**
   * 父主键
   */
  Long getParentId();

  /**
   * 显示标签
   */
  String getLabel();

}
