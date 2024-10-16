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
@TableName(value = "rules")
public class Rule {
    private int id; // 规则ID
    private int activityId; // 活动ID
    private String ruleDescription; // 规则描述
    private Date createdAt; // 创建时间
    private Date updatedAt; // 更新时间
}

