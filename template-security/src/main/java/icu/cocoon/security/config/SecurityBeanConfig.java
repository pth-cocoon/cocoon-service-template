package icu.cocoon.security.config;

import icu.cocoon.security.service.JwtService;
import icu.cocoon.security.service.RbacService;
import icu.cocoon.security.service.SecurityUserService;
import icu.cocoon.security.service.VerificationCodeService;
import icu.cocoon.security.service.impl.JwtServiceDefaultImpl;
import icu.cocoon.security.service.impl.RbacServiceDefaultImpl;
import icu.cocoon.security.service.impl.SecurityUserServiceDefaultImpl;
import icu.cocoon.security.service.impl.VerificationCodeServiceDefaultImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityBeanConfig {

  /**
   * 默认密码处理器,没有额外配置密码处理器时使用BCryptPasswordEncoder作为密码处理器.
   *
   * @return PasswordEncoder.
   */
  @Bean
  @ConditionalOnMissingBean(PasswordEncoder.class)
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(RbacService.class)
  public RbacService rbacService() {
    return new RbacServiceDefaultImpl();
  }

  @Bean
  @ConditionalOnMissingBean(JwtService.class)
  public JwtService jwtService() {
    return new JwtServiceDefaultImpl();
  }

  @Bean
  @ConditionalOnMissingBean(VerificationCodeService.class)
  public VerificationCodeService verificationCodeService() {
    return new VerificationCodeServiceDefaultImpl();
  }

  @Bean
  @ConditionalOnMissingBean(SecurityUserService.class)
  public SecurityUserService securityUserService() {
    return new SecurityUserServiceDefaultImpl();
  }


  /**
   * 跨域.
   */
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(source);
  }

}
