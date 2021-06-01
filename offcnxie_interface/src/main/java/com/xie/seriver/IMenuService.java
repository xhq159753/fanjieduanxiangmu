package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Menu;
import com.xie.utils.util.PageResult;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IMenuService extends IService<Menu> {
    Map<String,Object> memberEcharts();




}
