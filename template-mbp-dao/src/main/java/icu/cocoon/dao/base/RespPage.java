package icu.cocoon.core.resp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xce
 * @date 2020/4/7  10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPage<T> implements IPageInfo<T> {

  private int code;
  private String message;
  private Collection<T> data;
  private long current;
  private long size;
  private long total;


  public static <T> RespPage<T> success(IPage<T> page) {
    return success(page, "成功");
  }

  public static <T> RespPage<T> success(IPage<T> page, String message) {
    RespPage<T> respPage = new RespPage<>();
    respPage.setCode(respPage.getSuccessCode());
    respPage.setMessage(message);
    respPage.setData(page.getRecords());
    respPage.setCurrent(page.getCurrent());
    respPage.setTotal(page.getTotal());
    respPage.setSize(page.getSize());
    return respPage;
  }


}
