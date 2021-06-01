package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.MemberMapper;
import com.xie.pojo.Member;

import com.xie.seriver.IMemberService;
import com.xie.utils.util.DateUtils;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Resource
    private MemberMapper memberMapper;

    @Override
    public PageResult listPage(QueryPageBean queryPageBean) {
        System.out.println(queryPageBean+"------------------------");
//        创建页面对象
        Page<Member> page=new Page(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
//        设置查询条件
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
//        表的名称，不是写实体类的属性名称
        if(queryPageBean.getQueryString()!=null && !queryPageBean.getQueryString().equals("")){
//            查询条件
            queryWrapper.like("name",queryPageBean.getQueryString()).or().like("idCard",queryPageBean.getQueryString());

        }
//        没有查询的条件不是全部查询


        Page<Member> checkitemPage=memberMapper.selectPage(page,queryWrapper);
        return  new PageResult(checkitemPage.getTotal(),checkitemPage.getRecords());
    }

    @Override
    public Integer todayNewMember() throws Exception {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        String s = DateUtils.parseDate2String(DateUtils.getToday());
        memberQueryWrapper.eq("regTime",s);
        return memberMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer totalMember() {
        return memberMapper.selectCount(null);
    }

    @Override
    public Integer thisWeekNewMember() {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        Date thisWeekMonday = DateUtils.getThisWeekMonday(DateUtils.getToday());
        memberQueryWrapper.between("regTime",thisWeekMonday,DateUtils.getToday());
        return memberMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer thisMonthNewMember() {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        Date thisWeekMonday = DateUtils.getFirstDay4ThisMonth();
        memberQueryWrapper.between("regTime",thisWeekMonday,DateUtils.getToday());
        return memberMapper.selectCount(memberQueryWrapper);
    }
}
