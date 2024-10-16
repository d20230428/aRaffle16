package com.araffle.araffle.Controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.araffle.araffle.Config.GlobalJsonStore;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.*;
import com.araffle.araffle.Service.GameService;
import com.araffle.araffle.Service.SkinService;
import com.araffle.araffle.Service.UserService;
import com.araffle.araffle.Service.UserSkinExchangeService;
import com.araffle.araffle.Util.QiniuUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RequestMapping("/GameController")
@RestController
@Slf4j
public class GameController {

    @Autowired
    private GlobalJsonStore globalJsonStore;

    @Autowired
    GameService gameService;

    @Autowired
    SkinService skinService;

    @Autowired
    UserService userService;

    @Autowired
    UserSkinExchangeService userSkinExchangeService;

    @RequestMapping("/selectAll")
    public RJSON selectAll(){
        RJSON rJson = new RJSON();
        List<Game> games = gameService.selectAll();
        // 将 JSON 字符串设置到 RJSON 对象中
        List<GameSkins> gameSkins = new ArrayList<>();
        boolean status = false;
        for (Game game : games) {
            if (!status) {
                List<Skin> skins = skinService.selectById(game.getId());
                GameSkins gameSkins1 = GameSkins.builder().id(game.getId()).gameName(game.getGameName()).gameImage(game.getGameImage()).description(game.getDescription()).gameImage(game.getGameImage()).createdAt(game.getCreatedAt()).updatedAt(game.getUpdatedAt()).skins(skins).build();
                gameSkins.add(gameSkins1);
                status = true;
            } else {
                GameSkins gameSkins1 = GameSkins.builder().id(game.getId()).gameName(game.getGameName()).gameImage(game.getGameImage()).description(game.getDescription()).gameImage(game.getGameImage()).createdAt(game.getCreatedAt()).updatedAt(game.getUpdatedAt()).build();
                gameSkins.add(gameSkins1);
            }
        }
        rJson.setSuccess(true);
        rJson.setData(gameSkins); // 假设 RJSON 类有一个 setData 方法用来存储数据
        return rJson;
    }

    //查询全部用户
    @RequestMapping("/selectAll2")
    public JSONObject selectAll2(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", gameService.selectAll().size());
        jsonObject1.put("data", gameService.selectAll());
        return jsonObject1;
    }

    //查询全部用户
    @RequestMapping("/selectAll3")
    public JSONObject selectAll3(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", skinService.selectAll().size());
        jsonObject1.put("data", skinService.selectAll());
        return jsonObject1;
    }

    //修改数据
    @RequestMapping("/update")
    public RJSON update(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        Game game = jsonObject.toBean(Game.class);
        gameService.update(game);
        rJson.setSuccess(true);
        rJson.setMessage("修改成功");
        return rJson;
    }

    //修改数据
    @RequestMapping("/update2")
    public RJSON update2(@RequestBody JSONObject jsonObject)
    {
        RJSON rJson = new RJSON();
        Skin skin = jsonObject.toBean(Skin.class);
        skinService.update(skin);
        rJson.setSuccess(true);
        rJson.setMessage("修改成功");
        return rJson;
    }

    //通过id查询数据
    @RequestMapping("/selectById")
    public RJSON selectById(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        List<Skin> skins = skinService.selectById(jsonObject.getInt("id"));
        rJson.setSuccess(true);
        rJson.setData(skins);
        return rJson;
    }

    //添加数据
    @RequestMapping("/insert")
    public RJSON insert(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        Game game = Game.builder().gameName(jsonObject.getStr("title")).gameImage(jsonObject.getStr("title2")).build();
        gameService.insert(game);
        rJson.setSuccess(true);
        rJson.setMessage("添加成功");
        return rJson;
    }
    //添加数据
    @RequestMapping("/insert2")
    public RJSON insert2(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        Skin skin = Skin.builder().gameId(jsonObject.getInt("title9")).skinName(jsonObject.getStr("title3")).skinImage(jsonObject.getStr("title4")).isPopular(jsonObject.getBool("close")).redeemedCount(jsonObject.getInt("title7")).cost(jsonObject.getInt("title8")).build();
        skinService.insert(skin);
        rJson.setSuccess(true);
        rJson.setMessage("添加成功");
        return rJson;
    }

    //删除数据
    @RequestMapping("/delete")
    public RJSON delete(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        gameService.delete(jsonObject.getInt("id"));
        rJson.setSuccess(true);
        rJson.setMessage("删除成功");
        return rJson;
    }

    //删除数据
    @RequestMapping("/delete2")
    public RJSON delete2(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        skinService.delete(jsonObject.getInt("id"));
        rJson.setSuccess(true);
        rJson.setMessage("删除成功");
        return rJson;
    }

    //通过id查询数据
    @RequestMapping("/selectById2")
    public RJSON selectById2(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        User user = userService.selectById(jsonObject.getInt("userId"));
        Skin skin = skinService.selectById2(jsonObject.getInt("skinId"));
        if (user.getCoins() >= skin.getCost()) {
            rJson.setSuccess(true);
        } else {
            rJson.setSuccess(false);
        }
        return rJson;
    }

    @RequestMapping("/buySkin")
    public RJSON buySkin(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId,@RequestParam("skinId") int skinId,@RequestParam("username") String username) throws IOException {
        RJSON rJson = new RJSON();
        User user = userService.selectById(userId);
        Skin skin = skinService.selectById2(skinId);
        if (user.getCoins() >= skin.getCost()) {
            userService.addCoin(user.getId(), -skin.getCost());
            String imgPath = QiniuUploadUtil.uploadFile(file);
            String simpleUUID = IdUtil.simpleUUID();
            //当前日期字符串，格式：yyyy-MM-dd
            String today= DateUtil.today();
            Date date = DateUtil.parse(today, "yyyy-MM-dd");
            UserSkinExchange userSkinExchange = UserSkinExchange.builder().userId(userId).gameId(skin.getGameId()).skinId(String.valueOf(skin.getId())).username(username).orderNumber(simpleUUID).gameScreenshot(imgPath).executionStatus(0).createdAt(date).build();
            userSkinExchangeService.insert(userSkinExchange);
            rJson.setSuccess(true);
            rJson.setMessage("兑换成功");
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("金币不足");
        }
        return rJson;
    }

    //查询皮肤
    @RequestMapping("/selectSkin")
    public RJSON selectSkin(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        JSONArray jsonArray = globalJsonStore.getGlobalJsonArray();
        int id = jsonObject.getInt("id") - 1;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (id == i) {
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                rJson.setSuccess(true);
                rJson.setData(jsonArray1);
                return rJson;
            }
        }
        rJson.setSuccess(false);
        return rJson;
    }

    //查询所有数据
    @RequestMapping("/selectAll4")
    public JSONObject selectAll4() {
        List<UserSkinExchange> list = userSkinExchangeService.selectList();
        JSONArray jsonArray = new JSONArray();
        for (UserSkinExchange userSkinExchange : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNumber", userSkinExchange.getOrderNumber());
            jsonObject.put("username", userSkinExchange.getUsername());
            jsonObject.put("gameScreenshot", userSkinExchange.getGameScreenshot());
            if (userSkinExchange.getExecutionStatus() == 0) {
                jsonObject.put("executionStatus", "兑换中");
            } else if (userSkinExchange.getExecutionStatus() == 1) {
                jsonObject.put("executionStatus", "兑换完成");
            } else {
                jsonObject.put("executionStatus", "兑换失败");
            }
            Game game = gameService.selectById(userSkinExchange.getGameId());
            jsonObject.put("gameName", game.getGameName());
            Skin skin = skinService.selectById2(Integer.parseInt(userSkinExchange.getSkinId()));
            jsonObject.put("skinName", skin.getSkinName());
            User user = userService.selectById(userSkinExchange.getUserId());
            jsonObject.put("name", user.getUsername());
            jsonArray.put(jsonObject);
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", userSkinExchangeService.selectList().size());
        jsonObject1.put("data", jsonArray);
        return jsonObject1;
    }


    @RequestMapping("/selectAll5")
    public RJSON selectAll5(@RequestBody JSONObject jsonObject) {
        RJSON rJson = new RJSON();
        List<UserSkinExchange> list = userSkinExchangeService.selectByUserId(jsonObject.getInt("userId"));
        JSONArray jsonArray = new JSONArray();
        for (UserSkinExchange userSkinExchange : list) {
            JSONObject object = new JSONObject();
            Skin skin = skinService.selectById2(Integer.parseInt(userSkinExchange.getSkinId()));
            object.put("skinName", skin.getSkinName());
            object.put("skinImage", skin.getSkinImage());
            object.put("coin", skin.getCost());
            //兑换时间
            object.put("createdAt", userSkinExchange.getCreatedAt());
            if (userSkinExchange.getExecutionStatus() == 0) {
                object.put("executionStatus", "兑换中");
            } else if (userSkinExchange.getExecutionStatus() == 1) {
                object.put("executionStatus", "兑换完成");
            } else {
                object.put("executionStatus", "兑换失败");
            }
            jsonArray.put(object);
        }
        return rJson;
    }
}
