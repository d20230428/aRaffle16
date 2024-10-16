package com.araffle.araffle.Util;

import java.util.Random;

public class CaptchaUtil {

    private static final int CODE_LENGTH = 6;

    /**
     * 生成一个6位随机数验证码
     * @return 验证码字符串
     */
    public int generateCaptchaCode() {
        Random random = new Random();
        int minValue = (int) Math.pow(10, CODE_LENGTH - 1);
        int maxValue = (int) Math.pow(10, CODE_LENGTH) - 1;
        int captchaNumber = random.nextInt(maxValue - minValue + 1) + minValue;
        return captchaNumber;
    }

    /**
     * 验证用户输入的验证码是否正确
     * @param userInput 用户输入的验证码
     * @param captchaCode 实际生成的验证码
     * @return 验证结果，true表示正确，false表示错误
     */
    public boolean verifyCaptcha(String userInput, String captchaCode) {
        return captchaCode.equals(userInput);
    }

    public static void main(String[] args) {
        CaptchaUtil captchaUtil = new CaptchaUtil();
        // 生成验证码
        int captchaCode = captchaUtil.generateCaptchaCode();
        System.out.println(captchaCode);
    }
}
