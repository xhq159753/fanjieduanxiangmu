package com.xie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xie.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface RoleMapper extends BaseMapper<Role> {
List<Role> listByUserId(int user_id);
}
