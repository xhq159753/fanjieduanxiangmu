package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.RoleMapper;
import com.xie.pojo.CheckgroupCheckitem;
import com.xie.pojo.Role;

import com.xie.seriver.IRoleService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> listByUserId(int user_id) {
        return roleMapper.listByUserId(user_id);
    }
}
