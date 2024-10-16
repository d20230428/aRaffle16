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
@TableName(value = "activities")
public class Activity {

    private int id; // 活动ID
    private String activityName; // 活动名称
    private Date startDate; // 开始日期
    private Date endDate; // 结束日期
    private Date createdAt; // 创建时间
    private Date updatedAt; // 更新时间
}
