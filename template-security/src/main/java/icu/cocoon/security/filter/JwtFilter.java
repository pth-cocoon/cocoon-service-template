package icu.cocoon.security.filter;

import icu.cocoon.security.exception.LoginException;
import icu.cocoon.security.service.JwtService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author xce
 * @date 2019/10/23 17:45
 */
public class JwtFilter extends OncePerRequestFilter {


  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

  public JwtFilter(UserDetailsService userDetailsService, JwtService jwtService) {
    this.userDetailsService = userDetailsService;
    this.jwtService = jwtService;
  }

  protected String getJwtToken(HttpServletRequest request) {
    String authInfo = request.getHeader(HttpHeaders.AUTHORIZATION);
    return StringUtils.removeStart(authInfo, "Bearer ");
  }

  /**
   * 根据请求头中的 Authorization 里的token校验用户.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String token = getJwtToken(request);
    if (!StringUtils.isEmpty(token)) {
      String username = jwtService.getUsernameFromToken(token);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
          throw new LoginException(this.getClass(), "未找到用户！");
        }
        if (jwtService.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
              null,
              userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    }
    chain.doFilter(request, response);
  }
}
