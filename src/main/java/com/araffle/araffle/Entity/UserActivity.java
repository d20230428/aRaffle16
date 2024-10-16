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
@TableName(value = "user_activity")
public class UserActivity {

    private int userId;
    private int skinId;
    private Date timeId;
    private boolean increaseChance;
}
