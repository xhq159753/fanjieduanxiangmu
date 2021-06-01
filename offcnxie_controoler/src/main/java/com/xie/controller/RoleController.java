package com.xie.controller;


import com.xie.pojo.Role;
import com.xie.pojo.RoleMenu;
import com.xie.seriver.IRoleMenuService;
import com.xie.seriver.IRoleService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    private IRoleService iRoleMenuService;
    @RequestMapping("/select")
    public Result select(){
        try {
            List<Role> list = iRoleMenuService.list();
            list.forEach(System.out::println);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
    @RequestMapping("/select1")
    public Result select1(Integer id){
        try {
            Role byId = iRoleMenuService.getById(id);
            System.out.println(byId+"byid+++++++++++++++");
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,byId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
    @RequestMapping("/insert")
    public Result insert(@RequestBody Role role){
        try {
            boolean save = iRoleMenuService.saveOrUpdate(role);
            System.out.println(save);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
    @RequestMapping("/update")
    public Result update(Role role){
        System.out.println(role+"aooajdfo_______________________________________");
        try {
           iRoleMenuService.updateById(role);
//            System.out.println(save);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
           iRoleMenuService.removeById(id);
//            System.out.println(save);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }

}

