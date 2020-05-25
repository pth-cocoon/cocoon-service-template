package icu.cocoon.system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.cocoon.core.resp.IResp;
import icu.cocoon.security.config.SecurityConfiguration;
import icu.cocoon.dao.base.Resp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends SecurityConfiguration {


  @Override
  public AuthenticationSuccessHandler getLoginSuccessHandler() {
    return (request, response, authentication) -> {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      SecurityContextHolder.getContext().setAuthentication(authentication);
      Map<String, String> map = new HashMap<>();
      map.put("token", jwtService.generateToken(userDetails.getUsername()));
      IResp<Map<String, String>> IResp = Resp.success(map);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(new ObjectMapper().writeValueAsString(IResp));
      log.info("{} 用户登陆成功！", userDetails.getUsername());
    };
  }

  @Override
  protected AuthenticationFailureHandler getLoginFailureHandler() {
    return (request, response, exception) -> {
      IResp<Map<String, String>> resp = Resp.error(exception.getMessage());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
    };
  }


  @Override
  public String[] getWhiteList() {
    List<String> whiteList = new ArrayList<>();
    whiteList.add("/public");
    return whiteList.toArray(new String[0]);
  }
}
