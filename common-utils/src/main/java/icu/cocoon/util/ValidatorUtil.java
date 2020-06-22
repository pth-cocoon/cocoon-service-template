package icu.cocoon.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author xce
 * @date 2020/3/19  17:58
 * @node 数据校验工具类，待补充
 */
public class ValidatorUtil {

  private static final int PHONE_LENGTH = 11;

  /**
   * 校验手机号是否合法.
   *
   * @param phone 手机号
   * @return true为合法.
   * @apiNote 目前仅校验长度11位，并且第一位为1. 考虑啊到现在177，166，147等手机号横行，暂未做其他判断.
   */
  public static boolean checkPhone(String phone) {
    if (StringUtils.isBlank(phone) || phone.length() != PHONE_LENGTH) {
      return false;
    }
    char[] numbers = phone.toCharArray();
    return numbers[0] == '1';
  }

}
