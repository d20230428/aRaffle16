package com.araffle.araffle.Util;

import java.util.Random;

public class RandomNicknameGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz"; // 用于生成前缀和后缀的字符集合

    public static String generateRandomNickname() {
        Random random = new Random();

        // 随机生成前缀（2个字符）
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            prefix.append(randomChar);
        }

        // 中间部分是固定的
        String middle = "***";

        // 随机生成后缀（1个字符）
        StringBuilder suffix = new StringBuilder();
        char randomSuffixChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
        suffix.append(randomSuffixChar);

        return prefix.toString() + middle + suffix.toString() + " 抽到一个皮肤"; // 返回组合的结果
    }

    public static void main(String[] args) {
        // 生成并输出 10 条随机数据
        for (int i = 0; i < 50; i++) {
            String randomNickname = generateRandomNickname();
            System.out.println(randomNickname);
        }
    }
}
