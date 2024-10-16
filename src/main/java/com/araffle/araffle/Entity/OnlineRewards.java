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
@TableName(value = "online_rewards")
public class OnlineRewards {

    public int id;
    public int requiredOnlineTime;
    public String rewardDescription;
    public String rewardDurationDescription;
    public int rewardCoins;
    public String rewardImage;
    public Date createdAt;
    public Date updatedAt;
}
