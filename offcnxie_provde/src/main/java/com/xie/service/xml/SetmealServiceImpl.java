package com.xie.service.xml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xie.mapper.*;
import com.xie.pojo.*;

import com.xie.seriver.ISetmealService;
import com.xie.utils.util.PageResult;
import com.xie.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private CheckgroupMapper checkgroupMapper;
    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;
    @Resource
    private CheckitemMapper checkitemMapper;

    @Override
    public void save1(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.insert(setmeal);
        for (Integer checkgrou : checkgroupIds) {
            SetmealCheckgroup setmealCheckgroup = new SetmealCheckgroup();
            setmealCheckgroup.setSetmealId(setmeal.getId());
            setmealCheckgroup.setCheckgroupId(checkgrou);
            setmealCheckgroupMapper.insert(setmealCheckgroup);

        }
    }

    @Override
    public PageResult listPage(QueryPageBean queryPageBean) {
        Page<Setmeal> setmealPage = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        String queryString = queryPageBean.getQueryString();
        QueryWrapper<Setmeal> setmealQueryWrapper = new QueryWrapper<>();
        if (queryString != null && !queryString.equals("")) {
            setmealQueryWrapper.like("code", queryString)
                    .or().like("name", queryString)
                    .or().like("helpCode", queryString);
        }
        Page<Setmeal> setmealPage1 = setmealMapper.selectPage(setmealPage, setmealQueryWrapper);
        return new PageResult(setmealPage1.getTotal(), setmealPage1.getRecords());
    }

    @Override
    public Setmeal getInfo(Integer id) {

        Setmeal setmeal = setmealMapper.selectById(id);
        //一个套餐包含多个检查组
        //查询中间表看一下这个套餐有什么检查组
        QueryWrapper<SetmealCheckgroup> setmealCheckgroupQueryWrapper = new QueryWrapper<>();
        setmealCheckgroupQueryWrapper.eq("setmeal_id", setmeal.getId());
        List<SetmealCheckgroup> setmealCheckgroups = setmealCheckgroupMapper.selectList(setmealCheckgroupQueryWrapper);
        if (setmealCheckgroups == null || setmealCheckgroups.size() == 0) {
            return setmeal;
        }
        List<Checkgroup> checkgroupList = new ArrayList<>();
        for (SetmealCheckgroup setmealCheckgroup : setmealCheckgroups) {
            Checkgroup byId = checkgroupMapper.getById(setmealCheckgroup.getCheckgroupId());
            QueryWrapper<CheckgroupCheckitem> checkgroupCheckitemQueryWrapper = new QueryWrapper<>();
            checkgroupCheckitemQueryWrapper.eq("checkgroup_id", setmealCheckgroup.getCheckgroupId());
            List<CheckgroupCheckitem> checkgroupCheckitems = checkgroupCheckitemMapper.selectList(checkgroupCheckitemQueryWrapper);
            if (checkgroupCheckitems != null && checkgroupCheckitems.size() > 0) {
                List<Checkitem> checkitemList = new ArrayList<>();
                for (CheckgroupCheckitem checkgroupCheckitem : checkgroupCheckitems) {
                    Checkitem byId1 = checkitemMapper.getById(checkgroupCheckitem.getCheckitemId());
                    checkitemList.add(byId1);
                }
                //把集合的数据设置到check group的对象之中
                byId.setCheckitemList(checkitemList);
            }
            checkgroupList.add(byId);
        }
        //把集合中的数据设置到setmeal的对象之中
        setmeal.setCheckgroupList(checkgroupList);
//一个检查组包含多个检查项 这些都要查出来


        return setmeal;
    }

    @Override
    public Map<String, Object> getCountSetmeal() {
        List<Map<String, Object>> countSetmeal = setmealMapper.getCountSetmeal();
        List<String> names = new ArrayList<>();
        for (Map<String, Object> StringobjectMap : countSetmeal) {
            names.add(StringobjectMap.get("name").toString());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("setmealCount", countSetmeal);
        map.put("setmealNames", names);
        return map;
    }
}
