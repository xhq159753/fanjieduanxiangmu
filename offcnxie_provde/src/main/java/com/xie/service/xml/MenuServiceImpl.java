package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.MemberMapper;
import com.xie.mapper.MenuMapper;
import com.xie.pojo.Member;
import com.xie.pojo.Menu;

import com.xie.seriver.IMenuService;
import com.xie.utils.util.DateUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private MemberMapper memberMapper;

    @Override
    public Map<String, Object> memberEcharts() {
        //过去一年时间内每月的会员总数量
        List<String> months = new ArrayList<>();//保存所有 月份
        List<Integer> memberCount = new ArrayList<>();//保存月份的会员的总数
        //获取当前时间
        Calendar instance = Calendar.getInstance();
        //把时间往前倒推12个月
        instance.add(Calendar.MONTH,-12);
        for (int i=0;i<12;i++){
            instance.add(Calendar.MONTH,1);
            Date time = instance.getTime();//获取日历的时间
//            时间的格式不对
            String format = new SimpleDateFormat("yyyy-mm").format(time);
            months.add(format);
        }
//        循环完毕moths是往后推12所有的日期
        //查询集合中的所有的月份的会员的总数
        for (String moth:months){
            QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
            memberQueryWrapper.le("regTime",moth+"31");
            Integer integer = memberMapper.selectCount(memberQueryWrapper);
            memberCount.add(integer);//把查询道德数据保存到membercount集合之中
        }
//把months与membercoutn放到map集合中一起返回
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("months",months);
        stringObjectHashMap.put("memberCount",memberCount);
        return stringObjectHashMap;
    }

}
