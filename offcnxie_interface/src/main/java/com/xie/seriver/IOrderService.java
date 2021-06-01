package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Order;
import com.xie.utils.util.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IOrderService extends IService<Order> {
Result submit(Map<String, Object> map) throws Exception;



Integer   todayOrderNumber() ;////今日预约数
Integer   todayVisitsNumber() throws Exception;////今日到诊数
Integer   thisWeekOrderNumber();////本周预约数
Integer   thisWeekVisitsNumber();////本周到诊数
Integer   thisMonthOrderNumber();////本月预约数
Integer   thisMonthVisitsNumber();////本月到诊数
    List<Map<String,Object>> hotSetmeal();
}
