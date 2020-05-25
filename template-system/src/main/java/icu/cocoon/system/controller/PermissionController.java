package icu.cocoon.system.controller;


import icu.cocoon.core.resp.IResp;
import icu.cocoon.system.base.BaseController;
import icu.cocoon.dao.base.Resp;
import icu.cocoon.system.entity.Permission;
import icu.cocoon.system.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping( "/system/permission" )
public class PermissionController extends BaseController<Permission, PermissionService> {

  @ApiOperation( "根据角色Id获取权限集合" )
  @GetMapping( "/roleId/{id:\\d+}" )
  public IResp<Collection<Permission>> getAllByRoleId(@PathVariable Long id) {
    return Resp.success(getService().findByRoleId(id));
  }
}

