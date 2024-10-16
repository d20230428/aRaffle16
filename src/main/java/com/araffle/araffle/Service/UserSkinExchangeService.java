package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.UserSkinExchangeDao;
import com.araffle.araffle.Entity.UserSkinExchange;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkinExchangeService {

    @Autowired
    UserSkinExchangeDao userSkinExchangeDao;

    @Autowired
    GameService gameService;

    @Autowired
    SkinService skinService;

    //插入数据
    public int insert(UserSkinExchange userSkinExchange) {
        return userSkinExchangeDao.insert(userSkinExchange);
    }

    //所有数据
    public List<UserSkinExchange> selectList() {
        return userSkinExchangeDao.selectList(null);
    }

    //通过用户id查询数据
    public List<UserSkinExchange> selectByUserId(int userId) {
        QueryWrapper<UserSkinExchange> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userSkinExchangeDao.selectList(queryWrapper);
    }
}
