package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.CheckitemMapper;
import com.xie.pojo.Checkitem;
import com.xie.seriver.ICheckitemService;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;


import org.apache.dubbo.config.annotation.Service;



import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class CheckitemServiceImpl extends ServiceImpl<CheckitemMapper, Checkitem> implements ICheckitemService {
    @Resource
    private CheckitemMapper checkitemMapper;
    @Override
    public PageResult listPage(QueryPageBean queryPageBean) {
        System.out.println(queryPageBean+"------------------------");
//        创建页面对象
        Page<Checkitem> page=new Page(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
//        设置查询条件
        QueryWrapper<Checkitem> queryWrapper = new QueryWrapper<>();
//        表的名称，不是写实体类的属性名称
        if(queryPageBean.getQueryString()!=null && !queryPageBean.getQueryString().equals("")){
//            查询条件
            queryWrapper.like("name",queryPageBean.getQueryString()).or().like("code",queryPageBean.getQueryString());

        }
//        没有查询的条件不是全部查询
        Page<Checkitem> checkitemPage=checkitemMapper.selectPage(page,queryWrapper);
        System.out.println(new PageResult(checkitemPage.getTotal(),checkitemPage.getRecords())+"ckeckitemserviceimpaoijdfoaj*********************************");
        return  new PageResult(checkitemPage.getTotal(),checkitemPage.getRecords());

    }

}
