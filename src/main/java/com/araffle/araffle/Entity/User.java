package com.araffle.araffle.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "users")
public class User {

    private int id;
    private String username;
    @JsonIgnore // 添加这个注解来排除userKey
    private String password;
    @JsonIgnore // 添加这个注解来排除userKey
    private String phoneNumber;
    private String uuid;
    private int coins;
    private Date createTime;
    @JsonIgnore // 添加这个注解来排除userKey
    private int status;
    @JsonIgnore // 添加这个注解来排除userKey
    private String userKey;

}
