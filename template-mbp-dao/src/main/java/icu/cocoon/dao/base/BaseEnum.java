package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.enums.IEnum;
import java.io.Serializable;
import java.util.Arrays;

/**
 * BaseEnum 继承自MyBatisPlus的枚举接口，提供了部分默认实现
 * @param <T>
 */
public interface BaseEnum<T extends Serializable> extends IEnum<T> {

  BaseEnum<T>[] getEnums();

  default BaseEnum<T> getByValue(Integer value) {
    return Arrays.stream(getEnums()).filter(anEnum -> anEnum.getValue().equals(value)).findFirst().orElse(null);
  }
}
