package icu.cocoon.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 处理新增和更新的基础数据填充
 *
 * @author xce
 */
@Component
@Slf4j
public class MetaHandler implements MetaObjectHandler {


  /**
   * 新增数据执行
   *
   * @param metaObject
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);

  }

  /**
   * 更新数据执行
   *
   * @param metaObject
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
  }

}
