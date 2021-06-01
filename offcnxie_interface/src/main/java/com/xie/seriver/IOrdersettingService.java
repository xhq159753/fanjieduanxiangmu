package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Caldate;
import com.xie.pojo.Ordersetting;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IOrdersettingService extends IService<Ordersetting> {
void saveList(List<Ordersetting> ordersettingList);

List<Caldate> listOrdersetting(String date);


    void update1(String date, Integer number);
}
