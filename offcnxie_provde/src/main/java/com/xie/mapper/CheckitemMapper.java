package com.xie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xie.pojo.Checkitem;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface CheckitemMapper extends BaseMapper<Checkitem> {
Checkitem getById(int id);
}
