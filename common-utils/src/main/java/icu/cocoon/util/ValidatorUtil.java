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
   * @apiNote 目前仅校验长度11位，并且第一位为1. 考虑到现在177，166，147等手机号横行，暂未做其他判断.
   */
  public static boolean checkPhone(String phone) {
    if (StringUtils.isBlank(phone) || phone.length() != PHONE_LENGTH) {
      return false;
    }
    char[] numbers = phone.toCharArray();
    return numbers[0] == '1';
  }

  /**
   * 判断字符串是否包含中文
   * @param s 校验的字符串
   * @return 包含 返回true
   */
  public static boolean containsChinese(String s) {
    return s.codePoints().anyMatch(codepoint -> Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN);
  }

  /**
   * 校验字符是否是中文
   * @param c 校验的char
   * @return 是 返回ture
   */
  public static boolean isChineseByChar(char c){
    return Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN;
  }

  /**
   * 判断字符串是否仅有中文
   * @param s 校验的字符串
   * @return 校验通过返回true
   */
  public static boolean isChineseFull(String s) {
    for (char c : s.toCharArray()) {
      if (!isChineseByChar(c)) {
        return false;
      }
    }
    return true;
  }

}
