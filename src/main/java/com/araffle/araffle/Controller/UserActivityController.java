package com.araffle.araffle.Controller;

import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.UserActivity;
import com.araffle.araffle.Service.UserActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/UserActivityController")
@RestController
@Slf4j
public class UserActivityController {

    @Autowired
    UserActivityService userActivityService;

    //插入
    @RequestMapping("/insert")
    public RJSON insert(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        UserActivity userActivity = UserActivity.builder()
                .userId(jsonObject.getInt("userId"))
                .skinId(jsonObject.getInt("skinId"))
                .timeId(jsonObject.getDate("timeId"))
                .increaseChance(false)
                .build();
        int result = userActivityService.insert(userActivity);
        if (result > 0) {
            rJson.setSuccess(true);
            rJson.setMessage("插入成功");
            return rJson;
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("插入失败");
            return rJson;
        }
    }

    //修改数据
    @RequestMapping("/update")
    public RJSON update(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        boolean isOverFive = userActivityService.isOverFive(jsonObject.getInt("userId"), jsonObject.getInt("timeId"));
        if (isOverFive == false) {
            rJson.setSuccess(false);
            rJson.setMessage("每场只能参与一款皮肤的抽奖哦~");
            return rJson;
        } else {
            UserActivity userActivity = userActivityService.selectById(jsonObject.getInt("userId") ,jsonObject.getInt("skinId"));
            int result = userActivityService.update(userActivity);
            if (result > 0) {
                rJson.setSuccess(true);
                rJson.setMessage("修改成功");
                return rJson;
            } else {
                rJson.setSuccess(false);
                rJson.setMessage("修改失败");
                return rJson;
            }
        }
    }


}
