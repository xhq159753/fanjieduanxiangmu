package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.Checkitem;
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
public interface ICheckitemService extends IService<Checkitem> {

        PageResult listPage(QueryPageBean queryPageBean);


}
