package icu.cocoon.core.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Cocoon
 * @date 2020/4/9  12:05
 */
public interface IPageInfo {

  long getCurrent();

  long getSize();

  long getTotal();
}
