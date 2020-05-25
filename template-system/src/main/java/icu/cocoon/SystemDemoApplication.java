package icu.cocoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
@EnableAsync
@EnableCaching(proxyTargetClass = true)
public class SystemDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SystemDemoApplication.class, args);
  }

}
