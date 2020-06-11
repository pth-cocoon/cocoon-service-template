package icu.cocoon.dao.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.cocoon.core.resp.IResp;
import icu.cocoon.core.resp.RespPage;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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



  @Resource
  @Getter
  protected HttpServletRequest request;

  protected S getService() {
    return this.service;
  }


  protected HashMap<String, SFunction<T, ?>> getQueryFields() {
    return new HashMap<>();
  }

  protected LambdaQueryWrapper<T> getWrapper(Map<String, String> params) {
    HashMap<String, SFunction<T, ?>> map = getQueryFields();
    LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
    params.keySet().stream().filter(map::containsKey).forEach(k -> wrapper.eq(map.get(k), params.get(k)));
    return wrapper;
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
  public IResp<T> page(@RequestParam Map<String, String> params) {
    if (params == null) {
      return RespPage.success(getService().page(new Page<>()));
    }
    return RespPage.success(getService().page(getPage(params), getWrapper(params)));
  }

  @ApiOperation("创建")
  @PostMapping
  public IResp<Integer> create(@RequestBody T model) {
    service.create(model);
    return Resp.success();
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
