package icu.cocoon.system.entity.vo;


import icu.cocoon.system.entity.Menu;
import icu.cocoon.system.entity.Permission;
import icu.cocoon.system.entity.Role;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xce
 * @date 2020/3/13  10:42
 */
@EqualsAndHashCode( callSuper = true )
@Data
public class RoleVO extends Role {

  private List<Permission> permissionList;
  private List<Menu> menuList;
}
