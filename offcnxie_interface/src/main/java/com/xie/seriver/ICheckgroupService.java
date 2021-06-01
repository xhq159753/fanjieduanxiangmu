package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Checkgroup;
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
public interface ICheckgroupService extends IService<Checkgroup> {

    void save(Checkgroup checkgroup, Integer[] checkitemids);

    PageResult list(QueryPageBean queryPageBean);

    void update1(Checkgroup checkgroup, Integer[] checkitemids);
}
