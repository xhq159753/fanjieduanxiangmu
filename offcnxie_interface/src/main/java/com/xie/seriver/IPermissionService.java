package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> listBydUserId(int user_id);
}
