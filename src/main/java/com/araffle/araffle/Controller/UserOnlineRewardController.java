package com.araffle.araffle.Controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.OnlineRewards;
import com.araffle.araffle.Entity.UserOnlineReward;
import com.araffle.araffle.Service.OnlineRewardsService;
import com.araffle.araffle.Service.UserOnlineRewardService;
import com.araffle.araffle.Service.UserService;
import com.araffle.araffle.Service.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/UserOnlineRewardController")
@RestController
@Slf4j
public class UserOnlineRewardController {

    @Autowired
    UserOnlineRewardService userOnlineRewardService;

    @Autowired
    OnlineRewardsService onlineRewardsService;

    @Autowired
    UserService userService;
    //插入
    @RequestMapping("/insert")
    public RJSON insert(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        Date date = DateUtil.date();
        UserOnlineReward userOnlineReward = UserOnlineReward.builder().userId(jsonObject.getInt("userId")).rewardId(jsonObject.getInt("rewardId")).date(date).build();
        int result = userOnlineRewardService.insert(userOnlineReward);
        if (result > 0) {
            OnlineRewards onlineRewards = onlineRewardsService.selectById(jsonObject.getInt("rewardId"));
            userService.addCoin(jsonObject.getInt("userId"), onlineRewards.getRewardCoins());
            rJson.setSuccess(true);
            rJson.setMessage("插入成功");
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("插入失败");
        }
        return rJson;
    }
}
