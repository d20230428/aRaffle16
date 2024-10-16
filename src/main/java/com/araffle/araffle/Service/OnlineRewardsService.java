package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.OnlineRewardsDao;
import com.araffle.araffle.Entity.OnlineRewards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineRewardsService {

    @Autowired
    OnlineRewardsDao onlineRewardsDao;

    //查询所有数据
    public List<OnlineRewards> selectAll(){
        return onlineRewardsDao.selectList(null);
    }

    //修改代码
    public int update(OnlineRewards onlineRewards){
        return onlineRewardsDao.updateById(onlineRewards);
    }

    //查询单个数据
    public OnlineRewards selectById(int id){
        return onlineRewardsDao.selectById(id);
    }
}
