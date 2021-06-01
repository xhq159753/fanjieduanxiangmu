package com.xie.controller;


import com.xie.pojo.Checkitem;

import com.xie.seriver.ICheckitemService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/checkitem")

public class CheckitemController {


    @Reference
    private ICheckitemService iCheckitemService;

    @RequestMapping("save")
//    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result save(@RequestBody Checkitem checkitem) {
        try {
            boolean save = iCheckitemService.save(checkitem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("listPage")
//    @PreAuthorize("hasAuthority('USER_QUERY')")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean) {
        System.out.println(queryPageBean + "++__++__++_+_+");
        try {
            PageResult pageResult = iCheckitemService.listPage(queryPageBean);
            System.out.println(pageResult.getTotal()+">>>>>>>>>>>>>>>>>>>>");
            System.out.println(pageResult.getRows()+">>>>>>>>>>>>>>>>>>>>");


            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L, null);
        }
    }

    @RequestMapping("/remove")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    public Result remove(Integer id) {

        boolean a=false;

        try {
           a = iCheckitemService.removeById(id);
            System.out.println(a+"9999999999999999");
            return new Result(a, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(a+"false-------------");
            return new Result(a, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/getById")
//    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result getById(Integer id) {
        try {
            Checkitem byId = iCheckitemService.getById(id);
            if (byId == null) {
                return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
            }
            return new Result(false, MessageConstant.QUERY_CHECKITEM_SUCCESS, byId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result update(@RequestBody Checkitem checkitem) {
        System.out.println("修改成功"+"*****************************************");
        try {
            iCheckitemService.updateById(checkitem);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }


//    @RequestMapping("/listAll")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public Result listAll() {
        try {
            List<Checkitem> list = iCheckitemService.list();

            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }




}

