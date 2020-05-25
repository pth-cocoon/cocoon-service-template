package icu.cocoon.system.service.impl;


import icu.cocoon.security.service.SecurityUserService;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.service.RoleService;
import icu.cocoon.system.service.UserService;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements SecurityUserService {

  @Resource
  private UserService userService;

  @Resource
  private RoleService roleService;

  private String getCurrentUsername() {
    Authentication authentication = getCurrentAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return userDetails.getUsername();
  }

  @Override
  public UserDetails getByPhone(String phone) {
    return buildUserDetails(userService.getByPhone(phone));
  }

  public UserDetails getCurrentUserUserDetails() {
    String username = getCurrentUsername();
    User user = userService.getByUsername(username);
    return buildUserDetails(user);
  }

  private static Authentication getCurrentAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username + " 未找到指定用户！");
    }
    return buildUserDetails(user);
  }

  private UserDetails buildUserDetails(User user) {
    if (user == null) {
      return null;
    }
    return new UserDetails() {
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleService.findByUserId(user.getId()).stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
            .collect(Collectors.toList());
      }

      @Override
      public String getPassword() {
        return user.getPassword();
      }

      @Override
      public String getUsername() {
        return user.getUsername();
      }

      @Override
      public boolean isAccountNonExpired() {
        return true;
      }

      @Override
      public boolean isAccountNonLocked() {
        return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }

      @Override
      public boolean isEnabled() {
        return true;
      }
    };
  }


}
