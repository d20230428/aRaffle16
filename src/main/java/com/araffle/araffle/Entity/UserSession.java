package com.araffle.araffle.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_session")
public class UserSession {

    public int id;
    public int userId;
    public String recordTime;
    public Integer duration;
    public int status;
    public String deviceInfo;
    public String ipAddress;
    public String location;
    public Date createdAt;
    public Date updatedAt;

}
