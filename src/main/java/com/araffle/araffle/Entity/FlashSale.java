package com.araffle.araffle.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "flash_sales")
public class FlashSale {
    private int id;
    // 属性字段
    private String hour;        // 时间字段，例如 '9:00'
    private String endHour;
    private int status;         // 状态字段，数字表示
    private String startTime;   // 开始时间
    private String endTime;     // 结束时间
}
