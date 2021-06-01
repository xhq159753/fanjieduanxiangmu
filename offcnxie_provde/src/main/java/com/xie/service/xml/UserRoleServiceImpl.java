package com.xie.service.xml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.UserRoleMapper;
import com.xie.pojo.UserRole;

import com.xie.seriver.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
