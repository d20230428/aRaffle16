package com.araffle.araffle.Controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.FlashSale;
import com.araffle.araffle.Service.FlashSaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@RequestMapping("/FlashSaleController")
@RestController
@Slf4j
public class FlashSaleController {

    @Autowired
    private FlashSaleService flashSaleService;

    // 查询所有数据
    @RequestMapping("/selectAll")
    public RJSON selectAll() {
        RJSON rJson = new RJSON();
        List<FlashSale> flashSales = flashSaleService.selectAll();
        JSONArray jsonObject = new JSONArray();

        LocalTime currentTime = LocalTime.now(); // 获取当前时间

        for (FlashSale flashSale : flashSales) {
            LocalTime startTime = LocalTime.parse(flashSale.getStartTime());
            LocalTime endTime = LocalTime.parse(flashSale.getEndTime());
            JSONObject jsonObject1 = null;
            // 根据时间判断状态
            if (currentTime.isBefore(startTime)) {
                // 判断是否即将开始，距离开始时间还有 5 分钟
                if (currentTime.isAfter(startTime.minusMinutes(5))) {
                    flashSale.setStatus(1); //
                    jsonObject1 = new JSONObject(flashSale);
                    jsonObject1.put("timeState", "即将开始");
                } else {
                    flashSale.setStatus(0); // 未开始
                    jsonObject1 = new JSONObject(flashSale);
                    jsonObject1.put("timeState", "未开始");
                }
            } else if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime.minusMinutes(5))) {
                flashSale.setStatus(2); // 已开始
                jsonObject1 = new JSONObject(flashSale);
                jsonObject1.put("timeState", "已开始");
            } else if (currentTime.isAfter(endTime.minusMinutes(5)) && currentTime.isBefore(endTime)) {
                flashSale.setStatus(3); // 即将结束
                jsonObject1 = new JSONObject(flashSale);
                jsonObject1.put("timeState", "即将结束");
            } else if (currentTime.isAfter(endTime)) {
                flashSale.setStatus(4); // 已结束
                jsonObject1 = new JSONObject(flashSale);
                jsonObject1.put("timeState", "已结束");
            }

            jsonObject.add( jsonObject1); // 将每个 flashSale 对象添加到 jsonObject
        }
        Random random = new Random();
        int randomNumber = random.nextInt(10000 - 100 + 1) + 100;
        rJson.setMessage(String.valueOf(randomNumber));
        rJson.setData(jsonObject);
        rJson.setSuccess(true);
        return rJson;
    }
}
