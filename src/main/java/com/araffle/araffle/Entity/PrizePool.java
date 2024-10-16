package com.araffle.araffle.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "prize_pool")
public class PrizePool {

    private int id;
    private String prizeName;
    private String prizeImage;
    private String prizeType = "1";
    private BigDecimal prizeValue = BigDecimal.valueOf(0);
    private BigDecimal probability;
    private int stock = 10000;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int status = 1;
    private int angle;
    private String imageUrl;
    private int sortOrder;
}
