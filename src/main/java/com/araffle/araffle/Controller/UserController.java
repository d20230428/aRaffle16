package com.araffle.araffle.Controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Entity.User;
import com.araffle.araffle.Service.CacheService;
import com.araffle.araffle.Service.UserService;
import com.araffle.araffle.Util.CaptchaUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.araffle.araffle.Util.EncryptionUtil.*;
import static com.araffle.araffle.Util.SendSms.sendSms;

@RequestMapping("/UserController")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CacheService cacheService;

    public static String maskPhoneNumber(String phoneNumber) {
        // 确保手机号长度正确
        if (phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Invalid phone number length.");
        }
        return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7);
    }

    //发送验证码
    @RequestMapping("/sendCaptcha")
    public RJSON sendCaptcha(@RequestBody JSONObject jsonObject) throws Exception {
        RJSON rJson = new RJSON();
        String phone = jsonObject.getStr("phone");
        //判断是否发送过验证码
        if (cacheService.hasKey(phone)) {
            rJson.setSuccess(false);
            rJson.setMessage("已发送验证码，请勿重复发送");
            return rJson;
        }
        CaptchaUtil captchaUtil = new CaptchaUtil();
        // 生成验证码
        int captchaCode = captchaUtil.generateCaptchaCode();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("code", captchaCode);
        SendSmsResponse response = sendSms("雷元科技", "SMS_474575122", phone, jsonObject2);
        // 将 JSON 字符串解析为 JSONObject
        JSONObject jsonObject1 = JSONUtil.parseObj(new Gson().toJson(response).toString());
        // 提取 "body" 对象中的 "code" 字段
        String code = jsonObject1.getJSONObject("body").getStr("code");
        if (code.equals("OK")) {
            rJson.setSuccess(true);
            rJson.setMessage("发送验证码成功");
            cacheService.add(phone, captchaCode, 1, TimeUnit.MINUTES);
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("发送验证码失败");
        }
        return rJson;
    }

    //验证验证码
    @RequestMapping("/checkCaptcha")
    public RJSON checkCaptcha(@RequestBody JSONObject jsonObject) throws Exception {
        RJSON rJson = new RJSON();
        String phone = jsonObject.getStr("phone");
        String captchaCode = jsonObject.getStr("captchaCode");
        String captcha = cacheService.get(phone);
        if (captcha.equals(captchaCode)) {
            User user = userService.selectByPhoneNumber(phone);
            if (user == null) {
                String maskedNumber = maskPhoneNumber(phone);
                Date date = DateUtil.date();
                String simpleUUID = IdUtil.simpleUUID();
                User user1 = User.builder().username(maskedNumber).phoneNumber(phone).createTime(date).uuid(simpleUUID).build();
                int result = userService.insertUser(user1);
                User user2 = null;
                if (result > 0) {
                    user2 = userService.selectByPhoneNumber(phone);
                }
                rJson.setSuccess(true);
                rJson.setMessage("验证码正确");
                rJson.setData(user2);
            } else {
                rJson.setSuccess(true);
                rJson.setMessage("验证码正确");
                rJson.setData(user);
            }
        } else {
            rJson.setSuccess(false);
            rJson.setMessage("验证码错误");
        }
        return rJson;
    }

    //注册
    @RequestMapping("/register")
    public RJSON register(@RequestBody JSONObject jsonObject) throws Exception {
        RJSON rJson = new RJSON();
        String username = jsonObject.getStr("username");
        SecretKey key = generateKey();
        String originalPassword = jsonObject.getStr("password");
        String encryptedPassword = encrypt(originalPassword, key);
        String keyAsString = secretKeyToString(key);
        Date date = DateUtil.date();
        String simpleUUID = IdUtil.simpleUUID();
        log.info("username:"+username);
        log.info("password:"+encryptedPassword);
        log.info("date:"+date);
        log.info("uuid:"+simpleUUID);
        User user = User.builder().username(username).password(encryptedPassword).createTime(date).uuid(simpleUUID).userKey(keyAsString).build();
        int result = userService.insertUser(user);
        if (result > 0) {
            rJson.setData(user);
            rJson.setSuccess(true);
            rJson.setMessage("注册成功");
        } else {
            rJson.setSuccess(true);
            rJson.setMessage("注册失败");
        }
        return rJson;
    }

    //登录
    @RequestMapping("/login")
    public RJSON login(@RequestBody JSONObject jsonObject) throws Exception {
        RJSON rJson = new RJSON();
        String username = jsonObject.getStr("username");
        String selectUserKey = userService.selectUserKey(username);
        SecretKey decodedKey = stringToSecretKey(selectUserKey);
        String originalPassword = jsonObject.getStr("password");
        String encryptedPassword = encrypt(originalPassword, decodedKey);
        User user = userService.login(username,encryptedPassword);
        if (user != null) {
            rJson.setData(user);
            rJson.setSuccess(true);
            rJson.setMessage("登录成功");
        } else {
            rJson.setSuccess(true);
            rJson.setMessage("登录失败");
        }
        return rJson;
    }

    //selectCoin
    @RequestMapping("/selectCoin")
    public RJSON selectCoin(@RequestBody JSONObject jsonObject){
        RJSON rJson = new RJSON();
        int id = jsonObject.getInt("id");
        int coin = userService.selectCoin(id);
        rJson.setData(coin);
        rJson.setSuccess(true);
        rJson.setMessage("查询成功");
        return rJson;
    }

    //查询全部用户
    @RequestMapping("/selectAll")
    public JSONObject selectAll(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", 0);
        jsonObject1.put("msg", "");
        jsonObject1.put("count", userService.selectAll().size());
        jsonObject1.put("data", userService.selectAll());
        return jsonObject1;
    }


}
