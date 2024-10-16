package com.araffle.araffle.Dao;

import com.araffle.araffle.Entity.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends MPJBaseMapper<User> {

}
