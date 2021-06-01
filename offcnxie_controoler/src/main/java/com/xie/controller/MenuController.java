package com.xie.controller;


import com.xie.seriver.IMenuService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    private IMenuService iMenuService;
    @RequestMapping("/memberEcharts")
    public Result memberEcharts(){
        try {
            Map<String, Object> stringObjectMap = iMenuService.memberEcharts();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,stringObjectMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_FAIL);

        }

    }

}

