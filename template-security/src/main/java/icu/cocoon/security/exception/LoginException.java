package icu.cocoon.security.exception;

import icu.cocoon.core.base.BaseException;
import org.springframework.security.core.AuthenticationException;

/**
 * @author xce
 * @date 2020/1/9  9:43
 */
public class LoginException extends AuthenticationException implements BaseException {

  private int code;
  private final Class<?> clazz;

  public LoginException(Class<?> clazz, String msg) {
    super(msg);
    this.clazz = clazz;
  }

  @Override
  public int getCode() {
    return this.code;
  }

  @Override
  public Class<?> getClazz() {
    return this.clazz;
  }
}
