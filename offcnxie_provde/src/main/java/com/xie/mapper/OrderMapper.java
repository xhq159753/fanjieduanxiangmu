package com.xie.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xie.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface OrderMapper extends BaseMapper<Order> {
List<Map<String,Object>> hotSetmeal();


}
