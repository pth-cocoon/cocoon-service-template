package icu.cocoon.system.controller;


import icu.cocoon.core.resp.IResp;
import icu.cocoon.system.base.BaseController;
import icu.cocoon.dao.base.Resp;
import icu.cocoon.system.entity.Role;
import icu.cocoon.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Larva
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController<Role, RoleService> {

  @ApiOperation( "根据用户名查所拥有的角色" )
  @GetMapping( "/getByUsername/{username}" )
  public IResp<List<Role>> getByUsername(@PathVariable String username) {
    return Resp.success(getService().findByUsername(username));
  }

  @ApiOperation( "根据用户ID查询用户所有的角色" )
  @GetMapping( "/userId/{id:\\d+}" )
  public IResp<List<Role>> getByUserId(@PathVariable Long id) {
    return Resp.success(getService().findByUserId(id));
  }
}

