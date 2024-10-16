package com.araffle.araffle.Controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.PrizePool;
import com.araffle.araffle.Service.DrawRecordService;
import com.araffle.araffle.Service.PrizePoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping("/PrizePoolController")
@RestController
@Slf4j
public class PrizePoolController {

    @Autowired
    PrizePoolService prizePoolService;

    @Autowired
    DrawRecordService drawRecordService;

    //查询所有数据
    @RequestMapping("/selectAll")
    public RJSON selectAll()
    {
        RJSON rJson = new RJSON();
        rJson.setData(prizePoolService.selectAll());
        rJson.setSuccess(true);
        rJson.setMessage("奖品调取成功");
        return rJson;
    }

    //查询全部用户
    @RequestMapping("/selectAll2")
    public JSONObject selectAll2(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", prizePoolService.selectAll().size());
        jsonObject1.put("data", prizePoolService.selectAll());
        return jsonObject1;
    }
    //抽取奖池奖品
    @RequestMapping("/getPrize")
    public RJSON getPrize(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        int count = drawRecordService.getTodayDrawCount(jsonObject.getInt("userId"));
        boolean isOverFive = drawRecordService.isOverFive(jsonObject.getInt("userId"));
        if (isOverFive == true) {
            rJson.setSuccess(false);
            rJson.setMessage("今日剩余抽奖次数为0，明天再来哦~");
        } else {
            PrizePool prizePool = prizePoolService.getPrize(jsonObject.getInt("userId"));
            int angleSum = prizePoolService.getAngleSum(prizePool.getId(), jsonObject.getInt("userId"));
            JSONObject jsonObject1 = JSONUtil.parseObj(prizePool);
            jsonObject1.set("angleSum",angleSum);
            jsonObject1.set("count",count);
            log.info("prizePool: "+prizePool);
            rJson.setData(jsonObject1);
            rJson.setSuccess(true);
            rJson.setMessage("奖品获取成功");
        }
        return rJson;
    }

    //查询剩余抽奖次数
    @RequestMapping("/getCount")
    public RJSON getCount(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        int count = drawRecordService.getTodayDrawCount(jsonObject.getInt("userId"));
        //计算5减去count
        count = 5 - count;
        rJson.setData(count);
        rJson.setSuccess(true);
        rJson.setMessage("查询成功");
        return rJson;
    }

    //修改数据
    @RequestMapping("/update")
    public RJSON update(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        PrizePool prizePool = jsonObject.toBean(PrizePool.class);
        prizePoolService.update(prizePool);
        rJson.setSuccess(true);
        rJson.setMessage("修改成功");
        return rJson;
    }

    //添加数据
    @RequestMapping("/insert")
    public RJSON insert(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        //添加数据
        PrizePool prizePool = jsonObject.toBean(PrizePool.class);
        prizePoolService.insert(prizePool);
        rJson.setSuccess(true);
        rJson.setMessage("添加成功");
        return rJson;
    }

    //删除数据
    @RequestMapping("/delete")
    public RJSON delete(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        prizePoolService.delete(jsonObject.getInt("id"));
        rJson.setSuccess(true);
        rJson.setMessage("删除成功");
        return rJson;
    }

}
