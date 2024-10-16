package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.UserActivityDao;
import com.araffle.araffle.Entity.UserActivity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityDao userActivityDao;

    //插入
    public int insert(UserActivity userActivity) {
        return userActivityDao.insert(userActivity);
    }

    //修改数据
    public int update(UserActivity userActivity) {
        return userActivityDao.updateById(userActivity);
    }

    //查询单个数据
    public UserActivity selectById(int userId, int skinId) {
        UpdateWrapper<UserActivity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);
        updateWrapper.eq("skin_id", skinId);
        updateWrapper.set("increase_chance", true);
        return userActivityDao.selectOne(updateWrapper);
    }

    //查询用户今天是否有抽奖机会
    public boolean isOverFive(int userId, int timeId) {
        QueryWrapper<UserActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("time_id", timeId);
        UserActivity userActivity = userActivityDao.selectOne(queryWrapper);
        if (userActivity != null) {
            return false;
        } else {
            return true;
        }
    }

    //删除全部数据
    public int deleteAll() {
        return userActivityDao.delete(null);
    }
}
