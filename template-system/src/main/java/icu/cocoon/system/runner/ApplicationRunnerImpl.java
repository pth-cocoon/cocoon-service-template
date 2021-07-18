package icu.cocoon.system.runner;

import icu.cocoon.system.entity.User;
import icu.cocoon.system.service.UserService;
import javax.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

  @Resource
  private UserService userService;
  @Override
  public void run(ApplicationArguments args) throws Exception {
    User user = userService.getByUsername("admin");
    if (user==null){
      user = new User();
      user.setUsername("admin");
      user.setPassword("admin");
      user.setPhone("17777777777");
      user.setUpdateTime(System.currentTimeMillis());
      userService.create(user);
    }
  }
}