package com.araffle.araffle.Util;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class QiniuUploadUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "B476Yn6cXzW7A4PKTEBuBpwgA5ZjtT8BUVB6eRSA";
    private static final String SECRET_KEY = "2yHaU-uzV5lhXWi0OfCM3oL7i4SQ_2Mtkxn9arDh";

    // 要上传的空间
    private static final String bucketname = "raffle2024";
    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    public static String CDN = "https://raffle.leiyuanai.com";

    public static String uploadFile(MultipartFile imgURLFile) throws IOException {
        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString()+".png";
        String upToken = auth.uploadToken(bucketname);
        Response response = uploadManager.put(imgURLFile.getBytes(), randomUUIDString, upToken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println("defaultPutRet.key = " + defaultPutRet.key);
        System.out.println("defaultPutRet.hash = " + defaultPutRet.hash);
        String imgURL = CDN + "/" + defaultPutRet.key;
        System.out.println("imgURL = " + imgURL);
        return imgURL;
    }

    public static String uploadFileMp3(MultipartFile imgURLFile) throws IOException {
        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString()+".mp3";
        String upToken = auth.uploadToken(bucketname);
        Response response = uploadManager.put(imgURLFile.getBytes(), randomUUIDString, upToken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println("defaultPutRet.key = " + defaultPutRet.key);
        System.out.println("defaultPutRet.hash = " + defaultPutRet.hash);
        String imgURL = CDN + "/" + defaultPutRet.key;
        System.out.println("imgURL = " + imgURL);
        return imgURL;
    }

    public static String uploadFileMp4(MultipartFile imgURLFile) throws IOException {
        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString()+".mp4";
        String upToken = auth.uploadToken(bucketname);
        Response response = uploadManager.put(imgURLFile.getBytes(), randomUUIDString, upToken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println("defaultPutRet.key = " + defaultPutRet.key);
        System.out.println("defaultPutRet.hash = " + defaultPutRet.hash);
        String imgURL = CDN + "/" + defaultPutRet.key;
        System.out.println("imgURL = " + imgURL);
        return imgURL;
    }
}
