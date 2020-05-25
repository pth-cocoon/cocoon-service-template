package icu.cocoon.security.token;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author xce
 * @date 2020/3/19  17:41
 */
@Getter
@Setter
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

  private boolean authenticated = false;

  private final Object principal;
  private Object credentials;

  public SmsCodeAuthenticationToken(Object principal, Object credentials) {
    super(null);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(false);
  }

  public SmsCodeAuthenticationToken(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    super.setAuthenticated(true);
  }


}