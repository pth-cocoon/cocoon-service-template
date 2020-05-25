package icu.cocoon.core.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author xce
 * @date 2020/4/7  11:02
 */
public interface IResp<T> {

  /**
   * 返回业务码
   *
   * @return 返回业务码
   */
  int getCode();

  /**
   * 返回信息.
   *
   * @return 信息内容
   */
  String getMessage();

  /**
   * 返回数据对象.
   *
   * @return Object 可以是实体类，也可以是分页对象等.
   */
  Object getData();

  /**
   * 返回是否成功
   *
   * @return 成功 = true
   */
  default boolean isSuccess() {
    return getCode() == getSuccessCode();
  }

  /**
   * 成功状态码
   *
   * @return 0
   */
  @JsonIgnore
  default int getSuccessCode() {
    return 0;
  }

}