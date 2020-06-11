package icu.cocoon.core.resp;

/**
 * @author Cocoon
 * @date 2020/4/9  12:05
 */
public interface IPageInfo<T> extends IResp<T> {

  long getCurrent();

  long getSize();

  long getTotal();
}
