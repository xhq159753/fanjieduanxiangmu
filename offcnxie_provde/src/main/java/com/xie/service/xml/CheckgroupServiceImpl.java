package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.CheckgroupCheckitemMapper;
import com.xie.mapper.CheckgroupMapper;
import com.xie.pojo.Checkgroup;

import com.xie.pojo.CheckgroupCheckitem;
import com.xie.seriver.ICheckgroupService;
//import com.xie.utils.util.PageResult;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;
//import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup> implements ICheckgroupService {

    @Resource
    private CheckgroupMapper checkgroupMapper;
    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Override
    public void save(Checkgroup checkgroup, Integer[] checkitemids) {
//        添加检查
        int insert = checkgroupMapper.insert(checkgroup);
//        获取刚刚被添加的那个检查组对象的id
        Integer id = checkgroup.getId();
//        添加中间表的数据
        for (Integer che : checkitemids) {
            CheckgroupCheckitem checkgroupCheckitem = new CheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(id);
            checkgroupCheckitem.setCheckitemId(che);
            checkgroupCheckitemMapper.insert(checkgroupCheckitem);
        }


    }

    @Override
    public PageResult list(QueryPageBean queryPageBean) {

        Page<Checkgroup> checkgroupPage = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        String queryString=queryPageBean.getQueryString();
        QueryWrapper<Checkgroup> checkgroupQueryWrapper = new QueryWrapper<>();
        if(queryString!=null && !queryString.equals("")){
            checkgroupQueryWrapper.like("code",queryString)
                    .or().like("name",queryString)
                    .or().like("helpCode",queryString);
        }
        Page<Checkgroup> checkgroupPage1 = checkgroupMapper.selectPage(checkgroupPage, checkgroupQueryWrapper);
        System.out.println( new PageResult(checkgroupPage1.getTotal(),checkgroupPage1.getRecords())+"checkgroupservicelmpl----------------------------");
        return new PageResult(checkgroupPage1.getTotal(),checkgroupPage1.getRecords());
    }

    @Override
    public void update1(Checkgroup checkgroup, Integer[] checkitemids) {
        checkgroupMapper.updateById(checkgroup);//修改检查组的的信息
//        选择要删除的中间的原来的数据
        checkgroupCheckitemMapper.delete(new QueryWrapper<CheckgroupCheckitem>().eq("checkgroup_id",checkgroup.getId()));
//        重新添加中间表
        for (Integer checkitemid:checkitemids             ) {
            CheckgroupCheckitem checkgroupCheckitem=new CheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(checkgroup.getId());
            checkgroupCheckitem.setCheckitemId(checkitemid);
            checkgroupCheckitemMapper.insert(checkgroupCheckitem);

        }
    }
}
