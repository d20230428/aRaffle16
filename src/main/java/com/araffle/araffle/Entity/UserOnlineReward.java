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
@TableName(value = "user_online_rewards")
public class UserOnlineReward {

    private int id;
    private int userId;
    private int rewardId;
    private Date date;
}
