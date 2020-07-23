package icu.cocoon.core.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CoreBeanConfig {


  /**
   * Date格式化字符串
   */
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  /**
   * DateTime格式化字符串
   */
  private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  /**
   * Time格式化字符串
   */
  private static final String TIME_FORMAT = "HH:mm:ss";

  @Bean
  @ConditionalOnMissingBean(Jackson2ObjectMapperBuilderCustomizer.class)
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    log.warn("默认序列化反序列化配置");
    log.warn("目前已集成配置：");
    log.warn("long 转字符串-防止损失精度");
    log.warn("localDateTime,localDate,localTime 相关配置");
    log.warn("mybatis-plus 枚举类映射");
    return builder -> builder
        .serializerByType(Long.class, ToStringSerializer.instance)
        .featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
        .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)))
        .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
        .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)))
        .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)))
        .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
        .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
  }


}
