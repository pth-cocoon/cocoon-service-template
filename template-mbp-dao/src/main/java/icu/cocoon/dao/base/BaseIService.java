package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author xce
 * @date 2020/4/20  14:54
 */
public interface BaseIService<T extends BaseEntity> {


  /**
   * 插入一条记录
   *
   * @param entity 实体对象
   * @return int
   */
  int create(T entity);

  /**
   * 批量插入数据.
   *
   * @param entityList 需要插入的实体
   * @return true is
   */
  boolean create(Collection<T> entityList);


  /**
   * 根据 ID 删除
   *
   * @param id 主键ID.
   * @return int
   */
  int removeById(Serializable id);

  /**
   * 根据id批量删除.
   *
   * @param ids 主键集合.
   */
  void removeByIds(Collection<Long> ids);

  /**
   * 根据wrapper查询
   *
   * @param wrapper 查询条件
   * @return int
   */
  int remove(Wrapper<T> wrapper);

  /**
   * 更新
   *
   * @param entity 实体.
   * @return int
   */
  int update(T entity);


  /**
   * 根据 entity 条件，查询一条记录
   *
   * @param queryWrapper 实体对象封装操作类（可以为 null）
   * @return 实体对象
   */
  T getOne(Wrapper<T> queryWrapper);


  /**
   * 查询全部.
   *
   * @return 返回全部实体.
   */
  List<T> list();

  /**
   * 根据id查询实体.
   *
   * @param ids id集合.
   * @return 实体集合.
   */
  List<T> listByIds(Collection<? extends Serializable> ids);

  /**
   * 根据id查询对象
   *
   * @param id 主键
   * @return 实体
   */
  T getById(Long id);

  /**
   * 分页查询.
   *
   * @param page 分页对象.
   * @return 分页返回.
   */
  IPage<T> page(Page<T> page);



  /**
   * 分页查询.
   *
   * @param page    分页对象.
   * @param wrapper 查询条件.
   * @return 分页结果
   */
  <E extends IPage<T>> E page(E page, Wrapper<T> wrapper);


  /**
   * 根据wrapper查询
   *
   * @param wrapper 查询条件
   * @return 结果集合.
   */
  List<T> list(Wrapper<T> wrapper);


}
