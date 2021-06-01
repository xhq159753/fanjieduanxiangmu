package com.xie.service.xml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.PermissionMapper;
import com.xie.pojo.Permission;

import com.xie.seriver.IPermissionService;
import org.apache.dubbo.config.annotation.Service;
//import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
@Resource
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> listBydUserId(int user_id) {
        return permissionMapper.listBydUserId(user_id);
    }
}
