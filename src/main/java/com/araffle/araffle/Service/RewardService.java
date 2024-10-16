package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.RewardDao;
import com.araffle.araffle.Entity.Game;
import com.araffle.araffle.Entity.Reward;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    @Autowired
    RewardDao rewardDao;

    //获取所有数据
    public List<Reward> selectAll(){
        //升序
        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        //升序
        queryWrapper.orderByAsc("sort_order");
        return rewardDao.selectList(null);
    }

    //通过id查询数据
    public Reward selectById(int id){
        return rewardDao.selectById(id);
    }

    //修改数据
    public int update(Reward reward){
        return rewardDao.updateById(reward);
    }
}
