package com.araffle.araffle.Service;

import cn.hutool.core.date.DateUtil;
import com.araffle.araffle.Dao.UserOnlineRewardDao;
import com.araffle.araffle.Entity.UserOnlineReward;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserOnlineRewardService {

    @Autowired
    UserOnlineRewardDao userOnlineRewardDao;

    //插入
    public int insert(UserOnlineReward userOnlineReward) {
        return userOnlineRewardDao.insert(userOnlineReward);
    }

    //查询今天已经领取的奖励
    public boolean selectById(int userId, int rewardId) {
        QueryWrapper<UserOnlineReward> queryWrapper = new QueryWrapper<>();
        String today= DateUtil.today();
        Date date = DateUtil.parse(today, "yyyy-MM-dd");
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("reward_id", rewardId);
        queryWrapper.eq("date", date);
        UserOnlineReward userOnlineReward = userOnlineRewardDao.selectOne(queryWrapper);
        System.out.println(userOnlineReward);
        if (userOnlineReward == null) {
            return false;
        } else {
            return true;
        }
    }
}
