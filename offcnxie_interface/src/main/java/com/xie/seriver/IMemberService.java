package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Member;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IMemberService extends IService<Member> {

    PageResult listPage(QueryPageBean queryPageBean);


    Integer  todayNewMember() throws Exception;// :0,//本日新增会员数
    Integer  totalMember();////总会员数
    Integer  thisWeekNewMember();////本周新增会员数
    Integer  thisMonthNewMember();////本月新增会员数
}
