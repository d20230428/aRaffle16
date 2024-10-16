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
@TableName(value = "rewards")
public class Reward {

    private int id;
    private int dayNumber;
    private String rewardName;
    private String rewardDescription;
    private String rewardImage;
    private int rewardPoints;
    private Date createdAt;
    private Date updatedAt;
    private int sortOrder;


}
