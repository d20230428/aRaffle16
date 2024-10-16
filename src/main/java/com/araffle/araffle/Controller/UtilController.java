package com.araffle.araffle.Controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Util.QiniuUploadUtil;
import com.araffle.araffle.Util.RandomNicknameGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/UtilController")
@RestController
@Slf4j
public class UtilController {

    //insertUserSession
    @RequestMapping("/generateRandomNickname")
    public RJSON generateRandomNickname() {
        RJSON rJson = new RJSON();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 50; i++) {
            String randomNickname = RandomNicknameGenerator.generateRandomNickname();
            System.out.println(randomNickname);
            jsonArray.add(randomNickname);
        }
        rJson.setSuccess(true);
        rJson.setMessage("插入数据成功");
        rJson.setData(jsonArray);
        return rJson;
    }

    //七牛云上传文件
    @RequestMapping("/uploadFile")
    public RJSON uploadFile(@RequestParam("file") MultipartFile file) {
        RJSON rJson = new RJSON();
        try {
            String url = QiniuUploadUtil.uploadFile(file);
            rJson.setSuccess(true);
            rJson.setMessage("上传成功");
            rJson.setData(url);
        } catch (Exception e) {
            e.printStackTrace();
            rJson.setSuccess(false);
            rJson.setMessage("上传失败");
        }
        return rJson;
    }


}
