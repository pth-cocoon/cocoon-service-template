package icu.cocoon.dao.base;

import icu.cocoon.core.resp.IResp;
import lombok.Getter;

@Getter
public class Resp<T> implements IResp<T> {

  private int code;
  private String message;
  private T data;

  private Resp() {
    this.code = 1;
    this.message = "失败";
  }

  private Resp(String message) {
    this.message = message;
    this.code = getSuccessCode();
  }

  public static <T> Resp<T> success() {
    return new Resp<>("成功");
  }

  public static <T> Resp<T> success(T data) {
    Resp<T> IResp = success();
    IResp.data = data;
    return IResp;
  }

  private static <T> Resp<T> error() {
    Resp<T> resp = new Resp<>();
    resp.code = 1;
    resp.message = "失败";
    return resp;
  }

  public static <T> Resp<T> error(String message) {
    Resp<T> resp = error();
    resp.message = message;
    return resp;
  }


}
