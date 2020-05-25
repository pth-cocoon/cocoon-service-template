package icu.cocoon.system.controller;


import icu.cocoon.core.resp.IResp;
import icu.cocoon.security.service.SecurityUserService;
import icu.cocoon.dao.base.Resp;
import icu.cocoon.system.entity.Menu;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.entity.vo.LoginUserInfoVO;
import icu.cocoon.system.service.MenuService;
import icu.cocoon.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xce
 * @date 2020/3/13  17:35
 */
@RestController
@RequestMapping( "/sign" )
public class SignController {

  @Resource
  private UserService userService;

  @Resource
  private MenuService menuService;

  @Resource
  private SecurityUserService securityUserService;

  @ApiOperation( "退出登录（暂未实现）" )
  @PostMapping( "/signOut" )
  public IResp<String> logout() {
    return Resp.success();
  }

  @ApiOperation( "获取用户信息" )
  @GetMapping( "/info" )
  public IResp<LoginUserInfoVO> info() {
    String username = securityUserService.getCurrentUserUserDetails().getUsername();
    User user = userService.getByUsername(username);
    List<Menu> menuList = menuService.findByUserId(user.getId());
    return Resp.success(new LoginUserInfoVO(user, menuList));
  }
}
