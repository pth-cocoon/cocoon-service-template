package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.cocoon.core.resp.IResp;
import icu.cocoon.core.resp.RespPage;
import icu.cocoon.util.ReflectUtil;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xce
 * @date 2019/10/18 14:28
 */
@Slf4j
public abstract class BaseController<T extends BaseEntity, S extends BaseIService<T>> {

  @Autowired(required = false)
  protected S service;

  private final Class<T> entityClass;

  public BaseController() {
    Type superclass = this.getClass().getGenericSuperclass();
    ParameterizedType type = (ParameterizedType) superclass;
    Type[] types = type.getActualTypeArguments();
    this.entityClass = (Class<T>) types[0];
  }

  @Resource
  @Getter
  protected HttpServletRequest request;

  protected S getService() {
    return this.service;
  }


  protected Set<String> getQueryFields() {
    Class<T> entityClass = this.entityClass;
    return ReflectUtil.getFieldName(entityClass);
  }

  protected QueryWrapper<T> getWrapper(Map<String, String> params) {
    Set<String> fields = getQueryFields();
    QueryWrapper<T> orgWrapper = Wrappers.query();
    params.forEach((k, v) -> {
      if (fields.contains(k)) {
        orgWrapper.eq(StringUtils.camelToUnderline(k), v);
      }
    });
    return orgWrapper;
  }

  protected IPage<T> getPage(Map<String, String> params) {
    long page = 0;
    long size = 10;
    if (StringUtils.isNotBlank(params.get("page"))) {
      page = Integer.parseInt(params.get("page"));
      params.remove("page");
    }
    if (StringUtils.isNotBlank(params.get("size"))) {
      size = Integer.parseInt(params.get("size"));
      params.remove("size");
    }
    return new Page<T>(page, size).addOrder(OrderItem.desc("id"));

  }

  @ApiOperation("根据Id获取实体")
  @GetMapping("/{id:\\d+}")
  public IResp<T> getById(@PathVariable Long id) {
    return Resp.success(service.getById(id));
  }

  @ApiOperation("获取全部实体")
  @GetMapping
  public IResp<List<T>> getAll() {
    return Resp.success(service.list());
  }

  @ApiOperation("分页查询")
  @GetMapping("/page")
  public RespPage<T> page(@RequestParam Map<String, String> params) {
    if (params == null) {
      return RespPage.success(getService().page(new Page<>()));
    }
    return RespPage.success(getService().page(getPage(params), getWrapper(params)));
  }

  @ApiOperation("创建")
  @PostMapping
  public IResp<T> create(@RequestBody T model) {
    service.create(model);
    return Resp.success(model);
  }

  @ApiOperation("更新")
  @PutMapping
  public IResp<T> update(@RequestBody @Valid T model) {
    service.update(model);
    return Resp.success(model);
  }

  @ApiOperation("删除")
  @DeleteMapping("/{id:\\d+}")
  public IResp<String> delete(@PathVariable Long id) {
    service.removeById(id);
    return Resp.success();
  }


  @ApiOperation("批量删除")
  @DeleteMapping
  public IResp<String> deleteAll(@RequestBody Long[] ids) {
    getService().removeByIds(Arrays.asList(ids));
    return Resp.success();
  }


}
