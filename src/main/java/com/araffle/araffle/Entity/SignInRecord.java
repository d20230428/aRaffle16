package com.araffle.araffle.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sign_in_records")
public class SignInRecord {

    private int id;
    private int userId;
    private int rewardId;
    private String signInDate;
    private String createdAt;

    // 构造函数、toString()等可以在这里定义

}

