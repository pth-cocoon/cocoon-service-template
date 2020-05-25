package icu.cocoon.system.exception;

import icu.cocoon.core.base.BaseException;

/**
 * 数据库关系校验异常. 通常用于标记，数据存在关系，无法无法删除. Api返回结果
 *
 * @author Cocoon
 * @date 2020/4/20  16:40
 */
public class RelationCheckException extends RuntimeException implements BaseException {

  private int code;
  private Class<?> clazz;

  @Override
  public int getCode() {
    return this.code;
  }

  @Override
  public Class getClazz() {
    return this.clazz;
  }

  public RelationCheckException(Class clazz, String message) {
    super(message);
    this.clazz = clazz;
  }
}
