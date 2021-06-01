package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.OrdersettingMapper;
import com.xie.pojo.Caldate;
import com.xie.pojo.Ordersetting;

import com.xie.seriver.IOrdersettingService;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting> implements IOrdersettingService {

    @Resource
    private OrdersettingMapper ordersettingMapper;
    @Override
    public void saveList(List<Ordersetting> ordersettingList) {
        for (Ordersetting ordersetting:ordersettingList){
            QueryWrapper<Ordersetting> ordersettingQueryWrapper = new QueryWrapper<>();
//            先看看数据库是否有这个日期的数据
            ordersettingQueryWrapper.eq("orderDate",ordersetting.getOrderdate());
            Ordersetting ordersetting1=ordersettingMapper.selectOne(ordersettingQueryWrapper);
            if (ordersetting1==null){
//                没有这个日期
                ordersettingMapper.insert(ordersetting);
            }else {
                //存在这个日期
                ordersetting1.setNumber(ordersetting.getNumber());//修改新的可预约人数
                ordersettingMapper.updateById(ordersetting1);
            }
        }
    }

    @Override
    public List<Caldate> listOrdersetting(String date) {
        String beginTime=date+"-01";
        String endTime=date+"-31";
        QueryWrapper<Ordersetting> ordersettingQueryWrapper = new QueryWrapper<>();
        ordersettingQueryWrapper.between("orderDate",beginTime,endTime);
        List<Ordersetting> ordersettings=ordersettingMapper.selectList(ordersettingQueryWrapper);
        List<Caldate> list=new ArrayList<>();
//        把order setting的数据转换为前台需要的格式
        if (ordersettings!=null && ordersettings.size()>0){
            for (Ordersetting ordersetting:ordersettings){
                int dayOfMonth = ordersetting.getOrderdate().getDayOfMonth();
                Integer number = ordersetting.getNumber();
                Integer reservations = ordersetting.getReservations();
                list.add(new Caldate(dayOfMonth,number,reservations));
            }
        }
        return list;
    }

    @Override
    public void update1(String date, Integer number) {
        QueryWrapper<Ordersetting> ordersettingQueryWrapper = new QueryWrapper<>();
        ordersettingQueryWrapper.eq("orderDate",date);
        Ordersetting ordersetting = ordersettingMapper.selectOne(ordersettingQueryWrapper);
        if (ordersetting!=null){
            ordersetting.setNumber(number);
            ordersettingMapper.updateById(ordersetting);
        }else{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            LocalDate parse = LocalDate.parse(date, dateTimeFormatter);
            Ordersetting ordersetting1 = new Ordersetting(0, parse, number, 0);
            ordersettingMapper.insert(ordersetting1);
        }
    }
}
