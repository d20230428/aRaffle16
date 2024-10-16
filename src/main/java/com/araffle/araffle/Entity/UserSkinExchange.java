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
@TableName(value = "user_skin_exchange")
public class UserSkinExchange {

    private int id;
    private int userId;
    private int gameId;
    private String skinId;
    private String username;
    private String orderNumber;
    private String gameScreenshot;
    private int executionStatus; // 假设这个整数代表执行状态的不同值
    private Date createdAt;
}
