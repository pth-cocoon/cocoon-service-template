package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xce
 * @date 2019/10/17 16:25
 */
@Slf4j
public abstract class BaseService<M extends BaseMapper<T>, T extends BaseEntity> implements BaseIService<T> {

  @Autowired(required = false)
  protected M baseMapper;


  public M getMapper() {
    return baseMapper;
  }


  @Override
  public int create(T entity) {
    return getMapper().insert(entity);
  }

  @Override
  public boolean create(Collection<T> entityList) {
    entityList.forEach(this::create);
    return true;
  }


  @Override
  public int removeById(Serializable id) {
    return getMapper().deleteById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void removeByIds(Collection<Long> idList) {
    idList.forEach(this::removeById);
  }


  @Override
  public int update(T entity) {
    return getMapper().updateById(entity);
  }

  @Override
  public T getOne(Wrapper<T> queryWrapper) {
    return getMapper().selectOne(queryWrapper);
  }

  @Override
  public List<T> list() {
    return getMapper().selectList(Wrappers.emptyWrapper());
  }

  @Override
  public List<T> listByIds(Collection<? extends Serializable> ids) {
    return getMapper().selectBatchIds(ids);
  }

  @Override
  public T getById(Long id) {
    return getMapper().selectById(id);
  }

  @Override
  public IPage<T> page(Page<T> page) {
    return page(page, Wrappers.emptyWrapper());
  }


  @Override
  public <E extends IPage<T>> E page(E page, Wrapper<T> wrapper) {
    return getMapper().selectPage(page, wrapper);
  }


  @Override
  public List<T> list(Wrapper<T> wrapper) {
    return getMapper().selectList(wrapper);
  }

  @Override
  public int remove(Wrapper<T> queryWrapper) {
    return getMapper().delete(queryWrapper);
  }

}
