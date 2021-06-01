package com.xie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xie.pojo.Setmeal;

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
public interface SetmealMapper extends BaseMapper<Setmeal> {

    List<Map<String, Object>> getCountSetmeal();
}
