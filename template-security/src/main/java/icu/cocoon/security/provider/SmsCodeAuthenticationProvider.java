package icu.cocoon.security.provider;

import icu.cocoon.security.exception.LoginException;
import icu.cocoon.security.service.SecurityUserService;
import icu.cocoon.security.service.VerificationCodeService;
import icu.cocoon.security.token.SmsCodeAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author xce
 * @date 2020/3/20  9:37
 */
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

  private final SecurityUserService securityUserService;

  private final VerificationCodeService verificationCodeService;

  public SmsCodeAuthenticationProvider(SecurityUserService securityUserService,
      VerificationCodeService verificationCodeService) {
    this.securityUserService = securityUserService;
    this.verificationCodeService = verificationCodeService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String phone = authentication.getPrincipal().toString();
    String code = authentication.getCredentials().toString();
    if (!verificationCodeService.verify(phone, code)) {
      throw new LoginException(this.getClass(), "验证码有误，请核对！");
    }
    UserDetails user = securityUserService.getByPhone(phone);
    if (user == null) {
      throw new LoginException(this.getClass(), "无法获取用户信息");
    }
    return new SmsCodeAuthenticationToken(user, user.getPassword(), user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
  }
}