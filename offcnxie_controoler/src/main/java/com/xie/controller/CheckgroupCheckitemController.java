package com.xie.controller;


import com.xie.pojo.CheckgroupCheckitem;
import com.xie.seriver.ICheckgroupCheckitemService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/checkgroup-checkitem")
public class CheckgroupCheckitemController {
    @Reference
    private ICheckgroupCheckitemService iCheckgroupCheckitemService;
    @GetMapping("/{checkgroupId}")
    public Result getListCheckgroupId(@PathVariable("checkgroupId")Integer checkgroupId){
        try {
            Result result = iCheckgroupCheckitemService.listByChechgroupid(checkgroupId);
            return result;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUPCHECKITEM_SUCCESS);
        }


    }
//    @GetMapping("/{id}")
//    public Result getById(@PathVariable("id")Integer id){
//        try {
//            CheckgroupCheckitem byId = iCheckgroupCheckitemService.getById(id);
//            return new Result(true,MessageConstant.QUERY_CHECKGROUPCHECKITEM_SUCCESS,byId);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            return new Result(true,MessageConstant.QUERY_CHECKGROUPCHECKITEM_FAIL);
//        }
//    }

}

