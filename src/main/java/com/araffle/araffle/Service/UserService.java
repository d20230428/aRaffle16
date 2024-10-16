package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.UserDao;
import com.araffle.araffle.Entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    //插入数据
    public int insertUser(User user)
    {
        return userDao.insert(user);
    }

    //登录 login
    public User login(String username, String password)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return userDao.selectOne(queryWrapper);
    }

    //通过手机号查询用户
    public User selectByPhoneNumber(String phoneNumber)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", phoneNumber);
        return userDao.selectOne(queryWrapper);
    }

    //通过用户名查询用户的用户Key
    public String selectUserKey(String username)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userDao.selectOne(queryWrapper).getUserKey();
    }
    //查询用户的金币
    public int selectCoin(int id)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userDao.selectOne(queryWrapper).getCoins();
    }

    //增加用户的金币数
    public int addCoin(int id, int coin)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userDao.selectOne(queryWrapper);
        user.setCoins(user.getCoins() + coin);
        return userDao.update(user, queryWrapper);
    }

    //查询全部用户
    public List<User> selectAll()
    {
        return userDao.selectList(null);
    }

    //通过id查询用户
    public User selectById(int id)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userDao.selectOne(queryWrapper);
    }
}
