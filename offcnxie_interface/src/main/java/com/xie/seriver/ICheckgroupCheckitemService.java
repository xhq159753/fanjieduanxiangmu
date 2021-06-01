package com.xie.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xie.pojo.CheckgroupCheckitem;
import com.xie.utils.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface ICheckgroupCheckitemService extends IService<CheckgroupCheckitem> {

    Result listByChechgroupid(Integer checkgroupId);
}
