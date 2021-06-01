package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.CheckgroupCheckitemMapper;
import com.xie.pojo.CheckgroupCheckitem;
import com.xie.seriver.ICheckgroupCheckitemService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
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
public class CheckgroupCheckitemServiceImpl extends ServiceImpl<CheckgroupCheckitemMapper, CheckgroupCheckitem> implements ICheckgroupCheckitemService {

    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Override
    public Result listByChechgroupid(Integer checkgroupId) {
        QueryWrapper<CheckgroupCheckitem> checkgroupCheckitemQueryWrapper = new QueryWrapper<>();
        checkgroupCheckitemQueryWrapper.eq("checkgroup_id",checkgroupId);
        List<CheckgroupCheckitem> checkgroupCheckitems = checkgroupCheckitemMapper.selectList(checkgroupCheckitemQueryWrapper);
//        集合变为前台支持数据的格式
        ArrayList<Integer> integers = new ArrayList<>();
        for (CheckgroupCheckitem checkitemids:checkgroupCheckitems) {
            integers.add(checkitemids.getCheckitemId());
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUPCHECKITEM_SUCCESS);
    }
}
