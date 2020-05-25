package icu.cocoon.system.controller;


import icu.cocoon.core.resp.IResp;
import icu.cocoon.system.base.BaseController;
import icu.cocoon.dao.base.Resp;
import icu.cocoon.system.entity.User;
import icu.cocoon.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@RestController
@RequestMapping( "/system/user" )
public class UserController extends BaseController<User, UserService> {



  @ApiOperation( "修改密码" )
  @PutMapping( "/password" )
  public IResp<?> updatePassword(@RequestBody @Valid User user, BindingResult bindingResult) {
    User newUser = getService().getById(user.getId());
    newUser.setPassword(user.getPassword());
    getService().update(newUser);
    return Resp.success(newUser);
  }
}

