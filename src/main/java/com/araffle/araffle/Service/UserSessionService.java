package com.araffle.araffle.Service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.araffle.araffle.Dao.UserSessionDao;
import com.araffle.araffle.Entity.UserSession;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserSessionService {
    @Autowired
    UserSessionDao userSessionDao;

    //插入
    public int insertUserSession(int userId, int status) {
        UserSession userSession = null;
        Date date2 = DateUtil.date(Calendar.getInstance());
        Date date = DateUtil.date();
        String format = DateUtil.format(date2, "yyyy-MM-dd HH:mm:ss");
        userSession = UserSession.builder().userId(userId).status(status).recordTime(format).createdAt(date).updatedAt(date).build();
        return userSessionDao.insert(userSession);
    }

    // 查询用户今天在APP在线的秒数
    public long getUserOnlineSecondsToday(int userId) {
        // 获取今天的开始时间和结束时间
        String now = DateUtil.now();
        Date date = DateUtil.parse(now);
        Date startOfDay = DateUtil.beginOfDay(date);
        Date endOfDay = DateUtil.endOfDay(date);
        System.out.println("startOfDay:"+startOfDay);
        System.out.println("endOfDay:"+endOfDay);
        // 查询用户今天的在线记录
        List<UserSession> sessions = userSessionDao.selectList(
                new QueryWrapper<UserSession>()
                        .eq("user_id", userId)
                        .ge("record_time", startOfDay)
                        .le("record_time", endOfDay)
        );
        long totalSeconds = 0;
        Date startTime = null;
        Date lastStartTime = null;
        System.out.println("sessions:"+sessions);
        for (UserSession session : sessions) {
            if (session.getStatus() == 1) {
                Date date1 = DateUtil.parse(session.getRecordTime(), "yyyy-MM-dd HH:mm:ss");
                startTime = date1;
            } else if (session.getStatus() == 2) {
                Date date1 = DateUtil.parse(session.getRecordTime(), "yyyy-MM-dd HH:mm:ss");
                lastStartTime = date1;
                totalSeconds += DateUtil.between(lastStartTime, startTime, DateUnit.SECOND);
                startTime = date1;
                System.out.println("totalSeconds:"+totalSeconds);
            } else if (session.getStatus() == 3) {
                Date date1 = DateUtil.parse(session.getRecordTime(), "yyyy-MM-dd HH:mm:ss");
                lastStartTime = date1;
                totalSeconds += DateUtil.between(lastStartTime, startTime, DateUnit.SECOND);
                startTime = null;
                lastStartTime = null;
            }
        }

        return totalSeconds;
    }

    //查询所有数据
    public List<UserSession> selectAll(){
        return userSessionDao.selectList(null);
    }

}
