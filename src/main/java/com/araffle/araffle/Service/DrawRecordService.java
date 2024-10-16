package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.DrawRecordDao;
import com.araffle.araffle.Entity.DrawRecord;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DrawRecordService {

    @Autowired
    DrawRecordDao drawRecordDao;

    //插入数据
    public int insertDrawRecord(int userId, int prizeId) {
        DrawRecord drawRecord = DrawRecord.builder().userId(userId).prizeId(prizeId).build();
        return drawRecordDao.insert(drawRecord);
    }

    // 查询用户今天抽奖次数是否超过5次
    public boolean isOverFive(int userId) {
        LocalDate today = LocalDate.now();

        // 计算今天开始和结束的时间戳
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        // 创建查询条件
        QueryWrapper<DrawRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .ge("draw_time", startOfDay) // 大于等于开始时间
                .lt("draw_time", endOfDay);  // 小于结束时间

        // 获取今天的抽奖次数
        int count = drawRecordDao.selectCount(queryWrapper);
        return count >= 5; // 判断是否超过5次
    }

    // 查询用户今天抽奖次数
    public int getTodayDrawCount(int userId) {
        LocalDate today = LocalDate.now();

        // 计算今天开始和结束的时间戳
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        // 创建查询条件
        QueryWrapper<DrawRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .ge("draw_time", startOfDay) // 大于等于开始时间
                .lt("draw_time", endOfDay);  // 小于结束时间

        return drawRecordDao.selectCount(queryWrapper);
    }

}
