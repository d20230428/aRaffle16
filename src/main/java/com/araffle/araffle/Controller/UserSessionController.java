package com.araffle.araffle.Controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.OnlineRewards;
import com.araffle.araffle.Entity.UserOnlineReward;
import com.araffle.araffle.Entity.UserSession;
import com.araffle.araffle.Service.OnlineRewardsService;
import com.araffle.araffle.Service.UserOnlineRewardService;
import com.araffle.araffle.Service.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/SignInRecordController")
@RestController
@Slf4j
public class UserSessionController {

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    OnlineRewardsService onlineRewardsService;

    @Autowired
    UserOnlineRewardService userOnlineRewardService;

    //insertUserSession
    @RequestMapping("/insertUserSession")
    public RJSON insertUserSession(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        int userId = jsonObject.getInt("userId");
        int status = jsonObject.getInt("status");
        int result = userSessionService.insertUserSession(userId, status);
        if (result > 0) {
            rJson.setSuccess(true);
            rJson.setMessage("插入数据成功");
            rJson.setData(result);
            return rJson;
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("插入数据失败");
            return rJson;
        }
    }

    // 查询用户今天在APP在线的秒数
    @RequestMapping("/getUserOnlineSecondsToday")
    public RJSON getUserOnlineSecondsToday(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        int userId = jsonObject.getInt("userId");
        long onlineSeconds = userSessionService.getUserOnlineSecondsToday(userId);
        System.out.println(onlineSeconds);
//        List<UserSession> userSessionList = userSessionService.selectAll();
        List<OnlineRewards> onlineRewardsList = onlineRewardsService.selectAll();
        // 创建一个 JSONArray
        JSONArray jsonArray = new JSONArray();
        for (OnlineRewards onlineRewards : onlineRewardsList) {
            onlineRewards.setRewardImage("http://raffle.leiyuanai.com/%E7%BB%84%20254.png");
            JSONObject jsonObject1 = new JSONObject(onlineRewards);
            if (onlineSeconds >= onlineRewards.getRequiredOnlineTime()) {
                boolean isSignIn = userOnlineRewardService.selectById(userId, onlineRewards.getId());
                jsonObject1.put("isSignIn", isSignIn);
                jsonObject1.put("isActive", true);
                jsonArray.add(jsonObject1);
            }else {
                jsonObject1.put("isSignIn", false);
                jsonObject1.put("isActive", false);
                jsonArray.add(jsonObject1);
            }
        }
        rJson.setSuccess(true);
        rJson.setMessage("查询成功");
        rJson.setData(jsonArray);
        return rJson;
    }

    //selectAll
    @RequestMapping("/selectAll")
    public RJSON selectAll() {
        RJSON rJson = new RJSON();
        List<UserSession> userSessionList = userSessionService.selectAll();
        JSONArray jsonArray = new JSONArray();
        for (UserSession userSession : userSessionList) {
            JSONObject jsonObject1 = new JSONObject(userSession);
            jsonArray.put(jsonObject1);
        }
        rJson.setSuccess(true);
        rJson.setData(jsonArray);
        return rJson;
    }
}
