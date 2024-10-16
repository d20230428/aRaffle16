package com.araffle.araffle.Config;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.araffle.araffle.Entity.Skin;
import com.araffle.araffle.Service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
public class GlobalJsonStore {

    private JSONArray globalJsonArray;

    @Autowired
    SkinService skinService;

    @PostConstruct
    public void init() {
        resetData();
    }

    public JSONArray getGlobalJsonArray() {
        return globalJsonArray;
    }

    public void resetData() {
        this.globalJsonArray = new JSONArray();
        // 创建 4 个子数组
        for (int i = 0; i < 4; i++) {
            List<Skin> skins = skinService.selectRandom10();
            System.out.println("skins = " + skins);
            JSONArray jsonArray = new JSONArray();
            for (int j = 0; j < skins.size(); j++) {
                JSONObject jsonObject = new JSONObject(skins.get(j));
                Random random = new Random();
                // 产生10到100之间的随机整数（包括10但不包括100）
                int randomNumber = 10 + random.nextInt(91); // 91 是为了生成0到90之间的随机数，然后加10
                int randomNumber2 = 1 + random.nextInt(5); // 生成0到4的随机数，然后加1
                jsonObject.put("quantity", randomNumber);
                jsonObject.put("winRate", randomNumber2);
                jsonArray.put(jsonObject);
            }
            this.globalJsonArray.add(jsonArray);
            System.out.println("this.globalJsonArray = " + this.globalJsonArray);
        }
    }
}
