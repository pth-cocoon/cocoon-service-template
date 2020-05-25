package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import icu.cocoon.core.resp.IPageInfo;
import icu.cocoon.core.resp.IResp;
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
public class RespPage<T> implements IResp<T> {

  private int code;
  private String message;
  private Collection<T> data;
  private IPageInfo IPageInfo;





  public static <T> RespPage<T> success(IPage<T> page) {
    return success(page, "成功");
  }

  public static <T> RespPage<T> success(IPage<T> page, String message) {
    RespPage<T> respPage = new RespPage<>();
    respPage.setCode(respPage.getSuccessCode());
    respPage.setMessage(message);
    respPage.setData(page.getRecords());
    respPage.setIPageInfo(new IPageInfo() {
      @Override
      public long getCurrent() {
        return page.getCurrent();
      }

      @Override
      public long getSize() {
        return page.getSize();
      }

      @Override
      public long getTotal() {
        return page.getTotal();
      }
    });
    return respPage;
  }


}
