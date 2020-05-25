package icu.cocoon.security.factory;

import icu.cocoon.security.exception.LoginException;
import icu.cocoon.security.token.SmsCodeAuthenticationToken;
import java.util.Map;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author xce
 * @date 2020/3/23  11:17
 */
public class AuthenticationTokenFactory {

  /**
   * 根据接收到的参数，返回一个合适的认证token，目前支持，用户名密码，手机号验证码
   *
   * @param params 认证参数
   * @return token
   */
  public static AbstractAuthenticationToken getAuthenticationToken(Map<Object,Object> params) {
    try {
      params.forEach((k, v) -> v = v.toString().trim());
      if (params.get("username") != null && params.get("password") != null) {
        return new UsernamePasswordAuthenticationToken(params.get("username").toString(), params.get("password").toString());
      }
      if (params.get("phone") != null && params.get("code") != null) {
        return new SmsCodeAuthenticationToken(params.get("phone").toString(), params.get("code").toString());
      }
    } catch (Exception e) {
      throw new LoginException(AuthenticationTokenFactory.class, "登陆参数不完整！");
    }
     throw new LoginException(AuthenticationTokenFactory.class, "登陆参数不完整！");
  }

}