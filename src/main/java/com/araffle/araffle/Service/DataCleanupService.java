package com.araffle.araffle.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataCleanupService {

    @Autowired
    UserActivityService userActivityService;

    // 每天午夜执行，cron 表达式为 0 0 0 * * ?
//    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteOldData() {
        // 执行删除操作，例如：
        System.out.println("已删除所有数据");
        userActivityService.deleteAll(); // 删除所有数据，根据需要修改
    }
}
