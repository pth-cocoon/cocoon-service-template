package icu.cocoon.dao.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MbpBeanConfig {

  /**
   * MybatisPlus 默认分页配置，默认上限500
   */
  @ConditionalOnMissingBean(PaginationInterceptor.class)
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor().setDbType(DbType.MYSQL);
  }



}
