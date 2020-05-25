package icu.cocoon.system.service.impl;

import icu.cocoon.security.service.RbacService;
import icu.cocoon.system.entity.Permission;
import icu.cocoon.system.service.PermissionService;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 * @author xce
 * @date 2020/1/6  16:50
 */
@Slf4j
@Service("rbacService")
public class RbacServiceImpl implements RbacService {

  private final static String ADMIN_USERNAME = "admin";

  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Resource
  private PermissionService permissionService;

  public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
    log.info("权限校验");
    long start = Instant.now().toEpochMilli();
    String uri = request.getRequestURI();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      UserDetails details = (UserDetails) principal;
      if (ADMIN_USERNAME.equals(details.getUsername())) {
        log.warn("超管账号无条件放行");
        return true;
      }
      List<String> roleNameList = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList());
      Set<Permission> permissionSet = new HashSet<>();
      roleNameList.forEach(roleName -> permissionSet.addAll(permissionService.findByRoleName(roleName)));
      for (Permission permission : permissionSet) {
        if (antPathMatcher.match(permission.getUri(), request.getRequestURI())) {
          if (checkMethod(permission, request.getMethod())) {
            return true;
          }
        }
      }
      log.error("URI:{},无访问权限,用户名：{}", uri, details.getUsername());
    }
    log.info("权限校验耗时 {} ms", Instant.now().toEpochMilli() - start);
    return false;
  }

  private boolean checkMethod(Permission permission, String method) {
    switch (method) {
      case "POST":
        return permission.getPostMethod();
      case "GET":
        return permission.getGetMethod();
      case "DELETE":
        return permission.getDeleteMethod();
      case "PUT":
        return permission.getPutMethod();
    }
    return false;
  }
}
