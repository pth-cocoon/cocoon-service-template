package icu.cocoon.security.config;

import icu.cocoon.security.filter.JwtFilter;
import icu.cocoon.security.filter.LoginFilter;
import icu.cocoon.security.provider.PasswordAuthenticationProvider;
import icu.cocoon.security.provider.SmsCodeAuthenticationProvider;
import icu.cocoon.security.service.JwtService;
import icu.cocoon.security.service.SecurityUserService;
import icu.cocoon.security.service.VerificationCodeService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author xce
 * @date 2020/1/6  16:54
 */
public abstract class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Resource
  protected PasswordEncoder passwordEncoder;

  @Resource
  protected VerificationCodeService verificationCodeService;

  @Resource
  protected JwtService jwtService;

  @Resource
  protected SecurityUserService securityUserService;

  protected abstract AuthenticationSuccessHandler getLoginSuccessHandler();

  protected abstract AuthenticationFailureHandler getLoginFailureHandler();


  protected abstract String[] getWhiteList();


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    AbstractAuthenticationProcessingFilter loginFilter = new LoginFilter();
    List<AuthenticationProvider> providerList = new ArrayList<>();
    providerList.add(new PasswordAuthenticationProvider(securityUserService, passwordEncoder));
    providerList.add(new SmsCodeAuthenticationProvider(securityUserService, verificationCodeService));
    loginFilter.setAuthenticationManager(new ProviderManager(providerList));
    loginFilter.setAuthenticationSuccessHandler(getLoginSuccessHandler());
    loginFilter.setAuthenticationFailureHandler(getLoginFailureHandler());
    http.authorizeRequests()
        .antMatchers(getWhiteList()).permitAll()
        /* .antMatchers("/public/**", "/login", "/auth/**", "/sign/**", "/api/**").permitAll()
         .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
             "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagge‌r-ui.html").permitAll()*/
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .anyRequest().access("@rbacService.hasPermission(request,authentication)")
        .and().cors().and().csrf().disable()
        .addFilterBefore(new JwtFilter(securityUserService, jwtService),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(loginFilter, JwtFilter.class);
  }

}
