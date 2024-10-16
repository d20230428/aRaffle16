package com.araffle.araffle.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "skins")
public class Skin {
    private int id;                     // 唯一标识符，自增主键
    private int gameId;                 // 所属游戏的ID，外键，不能为空
    private String skinName;             // 皮肤名称，最长为100个字符，不能为空
    private String skinImage;            // 皮肤图片的URL，最长为255个字符，可以为空
    private boolean isPopular;           // 是否热门，布尔值，默认为 FALSE
    private int redeemedCount;           // 兑换人数，默认为0
    private int cost;                    // 兑换需要的金币数，不能为空
    private Timestamp createdAt;         // 创建时间，默认为当前时间
    private Timestamp updatedAt;         // 最后更新时间，默认为当前时间，并在记录更新时自动更新
}

