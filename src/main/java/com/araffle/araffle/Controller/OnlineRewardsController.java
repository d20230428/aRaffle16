package com.araffle.araffle.Controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.OnlineRewards;
import com.araffle.araffle.Service.OnlineRewardsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/OnlineRewardsController")
@RestController
@Slf4j
public class OnlineRewardsController {

    @Autowired
    OnlineRewardsService onlineRewardsService;

    //查询所有数据
    @RequestMapping("/selectAll")
    public RJSON selectAll() {
        RJSON rJson = new RJSON();
        List<OnlineRewards> onlineRewards = onlineRewardsService.selectAll();
        JSONArray jsonArray = new JSONArray();
        for (OnlineRewards onlineReward : onlineRewards) {
            JSONObject jsonObject = new JSONObject(onlineReward);
            jsonObject.put("isActive", false);
            jsonArray.add(jsonObject);
        }
        rJson.setSuccess(true);
        rJson.setData(jsonArray);
        rJson.setMessage("获取在线奖励");
        return rJson;
    }

    @RequestMapping("/selectAll2")
    public JSONObject selectAll2(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", onlineRewardsService.selectAll().size());
        jsonObject1.put("data", onlineRewardsService.selectAll());
        return jsonObject1;
    }

    //修改代码
    @RequestMapping("/update")
    public RJSON update(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        rJson.setSuccess(true);
        rJson.setData(onlineRewardsService.update(jsonObject.toBean(com.araffle.araffle.Entity.OnlineRewards.class)));
        rJson.setMessage("修改在线奖励");
        return rJson;
    }


}
