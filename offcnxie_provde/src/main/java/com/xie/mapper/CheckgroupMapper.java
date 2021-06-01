package com.xie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xie.pojo.Checkgroup;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface CheckgroupMapper extends BaseMapper<Checkgroup> {
Checkgroup getById(int id);


}
