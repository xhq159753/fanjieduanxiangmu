package com.xie.controller;


import com.xie.pojo.Permission;
import com.xie.seriver.IPermissionService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
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
@RequestMapping("/permission")
public class PermissionController {
@Reference
    private IPermissionService iPermissionService;
@RequestMapping("/select")
    public Result select(){
    try {
        List<Permission> list = iPermissionService.list();
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    } catch (Exception e) {
        e.printStackTrace();
        return new Result(true, MessageConstant.QUERY_SETMEAL_FAIL);

    }
}
}

