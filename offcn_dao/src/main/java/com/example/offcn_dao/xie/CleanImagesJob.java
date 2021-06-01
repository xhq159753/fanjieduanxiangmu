package com.example.offcn_dao.xie;

import com.xie.utils.util.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.File;
import java.util.Set;

public class CleanImagesJob {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Scheduled(cron = "")
    public void cleanImagesJob(){
        Set<String> difference = redisTemplate.opsForSet().difference(RedisConstant.SETMEAL_PIC_UPLOADPIC, RedisConstant.SETMEAL_PIC_DB);

        if (difference!=null && difference.size()>0){
            for (String img:difference){
                File file = new File("D:/eclipse——jee-oxygen-1a-win32/tupian/"+img);
                file.delete();
            }
        }
        redisTemplate.delete(RedisConstant.SETMEAL_PIC_UPLOADPIC);
        redisTemplate.delete(RedisConstant.SETMEAL_PIC_DB);
    }
}
