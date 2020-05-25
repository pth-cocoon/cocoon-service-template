package icu.cocoon.system.entity.vo;


import icu.cocoon.system.entity.Menu;
import icu.cocoon.system.entity.User;
import java.util.List;
import lombok.Data;

/**
 * @author xce
 * @date 2020/3/16  11:43
 */
@Data
public class LoginUserInfoVO {

  private Long userId;

  private String username;

  private String avatarUrl;

  private List<Menu> menuList;

  public LoginUserInfoVO(User user, List<Menu> menuList) {
    this.userId = user.getId();
    this.username = user.getUsername();
    this.avatarUrl = user.getAvatarUrl();
    this.menuList = menuList;
  }
}
