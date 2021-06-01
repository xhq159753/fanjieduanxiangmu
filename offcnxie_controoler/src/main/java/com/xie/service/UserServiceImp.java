package com.xie.service;


import com.xie.pojo.Permission;
import com.xie.pojo.Role;
import com.xie.pojo.User;
import com.xie.seriver.IPermissionService;
import com.xie.seriver.IRoleService;
import com.xie.seriver.IUserService;
import org.apache.dubbo.config.annotation.Reference;
//import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserDetailsService {
    @Reference
    private IRoleService roleService;
    @Reference
    private IPermissionService iPermissionService;
    @Reference
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUserName = iUserService.getByUserName(s);
        if (byUserName==null){
            throw new UsernameNotFoundException("用户名不正确");
        }
        List<GrantedAuthority> authorities=new ArrayList<>();
        List<Role> roles = roleService.listByUserId(byUserName.getId());
        for (Role role:roles){
            String keyword = role.getKeyword();
            authorities.add(new SimpleGrantedAuthority(keyword));
        }
        List<Permission> permissions = iPermissionService.listBydUserId(byUserName.getId());
        for(Permission permission:permissions){
            String keyword = permission.getKeyword();
            authorities.add(new SimpleGrantedAuthority(keyword));
        }

        return new org.springframework.security.core.userdetails.User(byUserName.getUsername(),byUserName.getPassword(),authorities);
    }
}
