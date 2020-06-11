package icu.cocoon.system.controller;


import icu.cocoon.core.resp.IResp;
import icu.cocoon.dao.base.BaseController;
import icu.cocoon.dao.base.Resp;
import icu.cocoon.system.entity.Menu;
import icu.cocoon.system.service.MenuService;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
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
@RequestMapping("/system/menu")
public class MenuController extends BaseController<Menu, MenuService> {

  @ApiOperation( "根据角色Id获取菜单集合" )
  @GetMapping( "/roleId/{id:\\d+}" )
  public IResp<Collection<Menu>> getAllByRoleId(@PathVariable Long id) {
    return Resp.success(getService().findByRoleId(id));
  }

  @ApiOperation( "根据角色Id获取菜单集合" )
  @GetMapping( "/userId/{id:\\d+}" )
  public IResp<Collection<Menu>> getAllByUserId(@PathVariable Long id) {
    return Resp.success(getService().findByRoleId(id));
  }

}

