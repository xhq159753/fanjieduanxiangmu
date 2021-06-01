package com.xie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xie.pojo.Permission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {
List<Permission> listBydUserId(int user_id);
}
