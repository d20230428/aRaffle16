package com.araffle.araffle.Controller;

import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Service.ActivityService;
import com.araffle.araffle.Service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/RuleController")
@RestController
@Slf4j
public class RuleController {

    @Autowired
    ActivityService activityService;

    @Autowired
    RuleService ruleService;


    @RequestMapping("/getActivity")
    public RJSON getActivity() {
        RJSON rJson = new RJSON();
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("rule", ruleService.selectRuleById(1));
        rJson.setData(jsonObject);
        rJson.setSuccess(true);
        rJson.setMessage("抽奖规则获取成功");
        return rJson;
    }
}
