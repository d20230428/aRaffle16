package com.araffle.araffle.Util;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.concurrent.CompletableFuture;
//验证码
public class SendSms {

    private static final String REGION_ID = "cn-hangzhou";
    private static final String ENDPOINT = "dysmsapi.aliyuncs.com";

    // 定义一个发送短信的方法
    public static SendSmsResponse sendSms(String signName, String templateCode, String phoneNumber, JSONObject templateParam) throws Exception {

        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5tApT4iHL6GzfQCyDYM6")
                .accessKeySecret("Iqen6WTe6Xh1eqFwdH5MsAShRHz9yS")
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region(REGION_ID)
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride(ENDPOINT)
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName(signName)
                .templateCode(templateCode)
                .phoneNumbers(phoneNumber)
                .templateParam(String.valueOf(templateParam))
                .build();

        // Synchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> futureResponse = client.sendSms(sendSmsRequest);
        SendSmsResponse response = futureResponse.get();

        // Close the client
        client.close();

        return response;
    }

}
