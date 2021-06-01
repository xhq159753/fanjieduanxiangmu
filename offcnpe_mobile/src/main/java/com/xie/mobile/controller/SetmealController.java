package com.xie.mobile.controller;


import com.xie.pojo.Setmeal;
import com.xie.seriver.ISetmealService;
import com.xie.utils.util.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private ISetmealService iSetmealService;
    @Value("${fileUploadPath}")
    private String fileUploadPath;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/upload")
    public String upload(@RequestParam("imgFile") MultipartFile multipartFile){
        File upload = MyFileUtils.upload(multipartFile, fileUploadPath);

if (upload!=null) {
    redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_DB, upload.getName());
}
        return upload.getName();
    }
    @RequestMapping("/save")
    public Result save(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try {
            iSetmealService.save1(setmeal,checkgroupIds);
//            保存数据的成功
            redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_UPLOADPIC,setmeal.getImg().substring(9));
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(true, MessageConstant.ADD_SETMEAL_FAIL);

        }
    }
    @RequestMapping("/listPage")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
         return    iSetmealService.listPage(queryPageBean);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new PageResult(0l,null);
        }
    }
@RequestMapping("/findInfoById/{id}")
    public Result getInfo(@PathVariable("id")Integer id){
    try {
        System.out.println(id);
        Setmeal info = iSetmealService.getInfo(id);
        return  new Result(true,"成功",info);
    } catch (Exception e) {

        e.printStackTrace();
        return  new Result(false,"失败");

    }
}




}

