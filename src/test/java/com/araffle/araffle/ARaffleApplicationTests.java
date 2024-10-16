package com.araffle.araffle;

import com.araffle.araffle.Dao.DailySkinDao;
import com.araffle.araffle.Dao.SkinDao;
import com.araffle.araffle.Service.CacheService;
import com.araffle.araffle.Service.DailySkinService;
import com.araffle.araffle.Service.FlashSaleService;
import com.araffle.araffle.Service.SkinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ARaffleApplicationTests {

    @Autowired
    SkinService skinService;

    @Autowired
    DailySkinDao dailySkinDao;

    @Autowired
    DailySkinService dailySkinService;
    @Autowired
    SkinDao skinDao;

    @Autowired
    FlashSaleService flashSaleService;

    @Autowired
    CacheService cacheService;


    @Test
    void contextLoads() {
        cacheService.add("test", 1234, 5, TimeUnit.MINUTES);
    }

}
