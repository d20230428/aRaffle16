package com.araffle.araffle.Controller;

import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.Reward;
import com.araffle.araffle.Service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/RewardController")
@RestController
@Slf4j
public class RewardController {

    @Autowired
    RewardService rewardService;

    @RequestMapping("/selectAll")
    public RJSON selectAll() {
        RJSON rJson = new RJSON();
        rJson.setData(rewardService.selectAll());
        rJson.setMessage("查询成功");
        rJson.setSuccess(true);
        return rJson;
    }

    //查询全部用户
    @RequestMapping("/selectAll2")
    public JSONObject selectAll2(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", rewardService.selectAll().size());
        jsonObject1.put("data", rewardService.selectAll());
        return jsonObject1;
    }

    //修改数据
    @RequestMapping("/update")
    public RJSON update(@RequestBody JSONObject jsonObject) {
        Reward reward = jsonObject.toBean(Reward.class);
        RJSON rJson = new RJSON();
        rJson.setData(rewardService.update(reward));
        rJson.setMessage("修改成功");
        rJson.setSuccess(true);
        return rJson;
    }
}
