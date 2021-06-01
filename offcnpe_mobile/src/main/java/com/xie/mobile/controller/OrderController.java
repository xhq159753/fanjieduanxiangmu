package com.xie.mobile.controller;


import com.xie.seriver.IOrderService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private IOrderService iOrderService;
@RequestMapping("/password")
    public Result order(@RequestBody Map<String,Object> map){
    System.out.println(map+"submit+++++++++++++++++++++++++++++++++++++++++++++++++++");

    try {
            Result submit = iOrderService.submit(map);
            System.out.println(submit+"submit+++++++++++++++++++++++++++++++++++++++++++++++++++");
        return new Result(true, MessageConstant.ORDER_FULL);
//            return  submit;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_FULL);
        }
    }
}

