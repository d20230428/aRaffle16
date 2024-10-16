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
@TableName(value = "draw_record")
public class DrawRecord {

    private int id;
    private int userId;
    private int prizeId;
    private Timestamp drawTime;
}
