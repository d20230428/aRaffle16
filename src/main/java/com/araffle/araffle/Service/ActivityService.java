package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.ActivityDao;
import com.araffle.araffle.Dao.RuleDao;
import com.araffle.araffle.Entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;

    //查询唯一的活动信息
    public Activity selectOne(int id) {
        return activityDao.selectById(id);
    }
}
