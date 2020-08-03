package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Collection;
import lombok.Data;

@Data
public class PageData<T>  {

  private long size;

  private long current;

  private long total;

  private long pageSum;

  private Collection<T> list;


  public PageData(IPage<T> page) {
    this.size = page.getSize();
    this.current = page.getCurrent();
    this.total = page.getTotal();
    this.pageSum = page.getPages();
    this.list = page.getRecords();

  }
}
