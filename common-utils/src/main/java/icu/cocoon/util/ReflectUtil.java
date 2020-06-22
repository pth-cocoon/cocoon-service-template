package icu.cocoon.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 反射工具类
 */
public class ReflectUtil {


  /**
   * 根据属性名获取属性值
   */
  public static Object getFieldValueByName(String fieldName, Object o) {
    try {
      String firstLetter = fieldName.substring(0, 1).toUpperCase();
      String getter = "get" + firstLetter + fieldName.substring(1);
      Method method = o.getClass().getMethod(getter);
      return method.invoke(o);
    } catch (Exception e) {
      return null;
    }
  }


  /**
   * 获取字段指定注解.
   * @param field Field
   * @param annotation 注解Class
   * @return Annotation
   */
  public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> annotation) {
    return field.getAnnotation(annotation);
  }

  public static<T> Set<String> getFieldName(Class<?> clazz){
    Field[] fields = clazz.getDeclaredFields();
    return Stream.of(fields).map(Field::getName).collect(Collectors.toSet());
  }

}
