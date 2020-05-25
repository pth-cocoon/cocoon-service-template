package icu.cocoon.system.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author xce
 * @date 2020/3/12  17:12
 */
@Component
public class PasswordHandler extends BaseTypeHandler {

  @Resource
  private PasswordEncoder passwordEncoder;


  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType)
      throws SQLException {
    preparedStatement.setString(i, passwordEncoder.encode(o.toString()));
  }

  @Override
  public Object getNullableResult(ResultSet resultSet, String s) {
    return resultSet;
  }

  @Override
  public Object getNullableResult(ResultSet resultSet, int i) {
    return resultSet;
  }

  @Override
  public Object getNullableResult(CallableStatement callableStatement, int i) {
    return callableStatement;
  }
}