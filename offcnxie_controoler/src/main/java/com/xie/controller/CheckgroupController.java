package com.xie.controller;


import com.xie.pojo.Checkgroup;
import com.xie.seriver.ICheckgroupService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;
import com.xie.utils.util.Result;

import org.apache.dubbo.config.annotation.Reference;

import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/checkgroup")
public class CheckgroupController {
    @Reference
    private ICheckgroupService iCheckgroupService;
    @PostMapping("/save")
    public Result save(@RequestBody Checkgroup checkgroup,Integer[] checkitemids){
        try {
            iCheckgroupService.save(checkgroup,checkitemids);
            return  new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            return  new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/listPage")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult list = iCheckgroupService.list(queryPageBean);
            System.out.println(list+"++++++++++++++++++++");
            return list;

        } catch (Exception exception) {
            exception.printStackTrace();
            return new PageResult(0l,null);
        }
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id")Integer id){
        try {
            Checkgroup byId = iCheckgroupService.getById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,byId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }


    @PostMapping("/update")
    public Result update(@RequestBody Checkgroup checkgroup,Integer[] checkitemids){
        try {
            iCheckgroupService.update1(checkgroup,checkitemids);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }


    @RequestMapping("/remove")
    public Result remove(Integer id) {

        boolean a=false;

        try {
            a = iCheckgroupService.removeById(id);
            System.out.println(a+"9999999999999999");
            return new Result(a, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(a+"false-------------");
            return new Result(a, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @GetMapping("/listAll")
    public Result listAll(){
        try {
            List<Checkgroup> list = iCheckgroupService.list();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }
}

