package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Setmeal;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface ISetmealService extends IService<Setmeal> {

    void save1(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult listPage(QueryPageBean queryPageBean);

    Setmeal getInfo(Integer id);

    Map<String, Object> getCountSetmeal();
}
