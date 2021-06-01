package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.MemberMapper;
import com.xie.mapper.OrderMapper;
import com.xie.mapper.OrdersettingMapper;
import com.xie.pojo.Member;
import com.xie.pojo.Order;
import com.xie.pojo.Ordersetting;
import com.xie.seriver.IOrderService;
import com.xie.utils.util.DateUtils;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrdersettingMapper ordersettingMapper;
    @Resource
    private MemberMapper memberMapper;


    @Override
    public Result submit(Map<String, Object> map) throws Exception {
//        检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        QueryWrapper<Ordersetting> ordersettingQueryWrapper = new QueryWrapper<>();
        String orderData=(String)map.get("orderDate");
        ordersettingQueryWrapper.eq("oderDate", DateUtils.parseString2Date(orderData));
        Ordersetting ordersetting = ordersettingMapper.selectOne(ordersettingQueryWrapper);
        if (ordersetting==null){//没有设置预约的设置
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //进行了预约的设置
        //检查用户所选项的预约是否已经约满
        if (ordersetting.getNumber()<=ordersetting.getReservations()){
            //约满了
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        //没有约满
        //检查是否是会员
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("phoneNumber",(String)map.get("telephone"));
        Member member = memberMapper.selectOne(memberQueryWrapper);
        if (member==null){
            //不是会员则会自动完成注册
            member=new Member();
            member.setPhonenumber((String)map.get("telephone"));
            member.setSex((String)map.get("sex"));
            member.setIdcard((String)map.get("idCard"));
            member.setName((String)map.get("name"));
            member.setRegtime(LocalDate.now());
            member.setPassword("123456");
            int insert=memberMapper.insert(member);
        }
        //是会员或者已经玩成注册
        //检查用户是否重复预约
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("member_id",member.getId());
        orderQueryWrapper.eq("orderDate",DateUtils.parseString2Date(orderData));
        orderQueryWrapper.eq("setmeal_id",map.get("setmealId"));
        Order order = orderMapper.selectOne(orderQueryWrapper);
        if (order!=null){
            //已经预约过了
            return new Result(false,MessageConstant.HAS_ORDERED);
        }
        //没有预约过，实现预约 创建order对象
        Order order1 = new Order();
        order1.setMemberId(member.getId());
        order1.setOrdertype("微信公众号预约");
        order1.setOrderdate(LocalDate.parse(orderData, DateTimeFormatter.ofPattern("yy-MM-dd")));
        order1.setSetmealId(Integer.parseInt((String)map.get("setmealId")));
        orderMapper.insert(order1);

        //预约成功，更新当前的已预约的人数
        ordersetting.setReservations(ordersetting.getReservations()+1);
        ordersettingMapper.updateById(ordersetting);
        return new Result(true,MessageConstant.ORDER_SUCCESS);



    }

    @Override
    public Integer
    todayOrderNumber() {
        QueryWrapper<Order> memberQueryWrapper = new QueryWrapper<>();
        String s = null;
        try {
            s = DateUtils.parseDate2String(DateUtils.getToday());
        } catch (Exception e) {
            e.printStackTrace();
        }
        memberQueryWrapper.eq("orderDate",s);
        return orderMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer todayVisitsNumber() throws Exception {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        String s = null;

            s = DateUtils.parseDate2String(DateUtils.getToday());

        queryWrapper.eq("orderDate",s);
        queryWrapper.eq("orderStatus","已到诊");
//        Integer integer = orderMapper.selectCount(objectQueryWrapper);
        return orderMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer thisWeekOrderNumber() {
        QueryWrapper<Order> memberQueryWrapper = new QueryWrapper<>();
        Date thisWeekMonday = DateUtils.getThisWeekMonday(DateUtils.getToday());

        memberQueryWrapper.between("orderDate",thisWeekMonday, DateUtils.getToday());
        return orderMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer thisWeekVisitsNumber() {
        QueryWrapper<Order> memberQueryWrapper = new QueryWrapper<>();
        Date thisWeekMonday = DateUtils.getThisWeekMonday(DateUtils.getToday());
        memberQueryWrapper.between("orderDate",thisWeekMonday,DateUtils.getToday());
        memberQueryWrapper.eq("orderStatus","已到诊");
        return orderMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer thisMonthOrderNumber() {
        QueryWrapper<Order> memberQueryWrapper = new QueryWrapper<>();
        Date firstDay4ThisMonth = DateUtils.getFirstDay4ThisMonth();
        memberQueryWrapper.between("orderDate",firstDay4ThisMonth,DateUtils.getToday());

        return orderMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer thisMonthVisitsNumber() {
        QueryWrapper<Order> memberQueryWrapper = new QueryWrapper<>();
        Date thisWeekMonday = DateUtils.getFirstDay4ThisMonth();
        memberQueryWrapper.between("orderDate",thisWeekMonday,DateUtils.getToday());
        memberQueryWrapper.eq("orderStatus","已到诊");
        System.out.println(
                orderMapper.selectCount(memberQueryWrapper)+"                orderMapper.selectCount(memberQueryWrapper)\n"
        );
        return orderMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public List<Map<String, Object>> hotSetmeal() {
        return orderMapper.hotSetmeal();
    }
}
