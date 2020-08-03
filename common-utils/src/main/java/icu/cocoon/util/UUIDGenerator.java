package icu.cocoon.util;

import java.util.UUID;

public class UUIDGenerator {

  public static UUIDGenerator getInstance() {
    return instance;
  }

  private static final UUIDGenerator instance = new UUIDGenerator();

  public static String uuid(){
    return instance.generateUUID();
  }

  private synchronized String generateUUID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

}
