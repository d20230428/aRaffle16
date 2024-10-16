package com.araffle.araffle.Scheduler;

import com.araffle.araffle.Config.GlobalJsonStore;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JsonResetScheduler {

    private final GlobalJsonStore globalJsonStore;

    public JsonResetScheduler(GlobalJsonStore globalJsonStore) {
        this.globalJsonStore = globalJsonStore;
    }

    @Scheduled(cron = "0 0 0 * * *") // 每天午夜 0 点
//    @Scheduled(cron = "0 * * * * *") // 每分钟
    public void resetJsonData() {
        globalJsonStore.resetData();
        System.out.println("全局 JSON 数据已在午夜重置。");
    }
}
