package icu.cocoon.util;

public class TimeStampUtil {

  private static final long SECOND = 1000;

  private static final long MINUTE = SECOND*60;

  private static final long HOUR = MINUTE*60;

  private static final long DAY = HOUR*24;

  private static final long WEEK = DAY*7;


  public static long getByDay(int day){
    return day*DAY;
  }

  public static long getByHour(int hour){
    return hour*HOUR;
  }

  public static long getByWeek(int week){
    return week*WEEK;
  }

}