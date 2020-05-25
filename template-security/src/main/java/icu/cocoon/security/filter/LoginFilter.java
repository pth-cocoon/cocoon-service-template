package icu.cocoon.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.cocoon.security.factory.AuthenticationTokenFactory;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author xce
 * @date 2020/1/13  9:55
 */
@Slf4j
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  private final static String POST_METHOD = "POST";

  private final static String DEFAULT_FILTER_PATTERN = "/login";

  /**
   * 无参构造函数，默认拦截POST方法和"/login"路径
   */
  public LoginFilter() {
    this(DEFAULT_FILTER_PATTERN, POST_METHOD);
  }

  /**
   * 构造器提供访问路径，以及只拦截Post请求，防止Option请求被拦截
   *
   * @param pattern 访问路径.
   * @param method  访问方法.
   */
  public LoginFilter(String pattern, String method) {
    super(new AntPathRequestMatcher(pattern, method));

  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException {
    if (!POST_METHOD.equals(request.getMethod())) {
      throw new AuthenticationServiceException("仅支持" + request.getMethod());
    }
    Map params = new ObjectMapper().readValue(request.getReader().readLine(), Map.class);
    Authentication manager;
    AbstractAuthenticationToken token = AuthenticationTokenFactory.getAuthenticationToken(params);
    setDetails(request, token);
    manager = this.getAuthenticationManager().authenticate(token);
    return manager;
  }

  protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
  }


}
