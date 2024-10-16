package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.RewardDao;
import com.araffle.araffle.Dao.RuleDao;
import com.araffle.araffle.Entity.Rule;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    @Autowired
    RuleDao ruleDao;

    //通过id查询规则
    public List<Rule> selectRuleById(int id) {
        QueryWrapper<Rule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id", id);
        return ruleDao.selectList(queryWrapper);
    }
}
