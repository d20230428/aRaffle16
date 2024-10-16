package com.araffle.araffle.Controller;

import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.Reward;
import com.araffle.araffle.Entity.SignInRecord;
import com.araffle.araffle.Service.RewardService;
import com.araffle.araffle.Service.SignInRecordService;
import com.araffle.araffle.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/SignInRecordController")
@RestController
@Slf4j
public class SignInRecordController {

    @Autowired
    SignInRecordService signInRecordService;

    @Autowired
    UserService userService;

    @Autowired
    RewardService rewardService;
    //插入
    @RequestMapping("/insertSignInRecord")
    public RJSON insertSignInRecord(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        int result = signInRecordService.insertSignInRecord(jsonObject.getInt("userId"), jsonObject.getInt("rewardId"));
        Reward reward = rewardService.selectById(jsonObject.getInt("rewardId"));
        int addCoin = userService.addCoin(jsonObject.getInt("userId"), reward.getRewardPoints());
        if (addCoin > 0) {
            rJson.setSuccess(true);
            rJson.setMessage("签到成功");
            return rJson;
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("签到失败");
            return rJson;
        }
    }

    //checkContinuousSignIn
    @RequestMapping("/checkContinuousSignIn")
    public RJSON checkContinuousSignIn(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        try {
            int result = signInRecordService.checkContinuousSignIn(jsonObject.getInt("userId"));
            rJson.setSuccess(true);
            rJson.setData(result);
            rJson.setMessage("获取连续签到次数成功");
            return rJson;
        } catch (Exception e) {
            rJson.setSuccess(false);
            rJson.setMessage("获取连续签到次数失败");
            return rJson;
        }
    }


    //checkTodaySignIn
    @RequestMapping("/checkTodaySignIn")
    public RJSON checkTodaySignIn(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        boolean result = signInRecordService.checkTodaySignIn(jsonObject.getInt("userId"));
        rJson.setSuccess(true);
        rJson.setData(result);
        rJson.setMessage("获取今日签到状态成功");
        return rJson;
    }
}
