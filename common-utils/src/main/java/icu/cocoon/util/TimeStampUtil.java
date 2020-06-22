package icu.cocoon.util;

/**
 * @author xce
 * @date 2020-06-22
 * @node 时间戳工具类
 */
public class TimeStampUtil {

  private static final long SECOND = 1000;

  private static final long MINUTE = SECOND * 60;

  private static final long HOUR = MINUTE * 60;

  private static final long DAY = HOUR * 24;

  private static final long WEEK = DAY * 7;


  /**
   * 按天获取时间戳。
   *
   * @param day 天数
   * @return 时间戳long
   */
  public static long getByDay(int day) {
    return day * DAY;
  }

  /**
   * 按小时获取时间戳。
   *
   * @param hour 小时数
   * @return 时间戳long
   */
  public static long getByHour(int hour) {
    return hour * HOUR;
  }

  /**
   * 按周获取时间戳。
   *
   * @param week 周数
   * @return 时间戳long
   */
  public static long getByWeek(int week) {
    return week * WEEK;
  }

}
