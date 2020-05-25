package icu.cocoon.system.handler;

import icu.cocoon.security.exception.LoginException;
import icu.cocoon.dao.base.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xce
 * @date 2019/10/23 15:29
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

  @ExceptionHandler(LoginException.class)
  public Resp<String> loginExceptionHandler(LoginException e) {
    log.info("触发异常拦截，message：{},class:{}.", e.getMessage(), e.getClazz());
    e.printStackTrace();
    return Resp.error(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public Resp<String> publicException(Exception e) {
    log.info("触发异常拦截，message：{}", e.getMessage());
    e.printStackTrace();
    return Resp.error(e.getMessage());
  }


}
