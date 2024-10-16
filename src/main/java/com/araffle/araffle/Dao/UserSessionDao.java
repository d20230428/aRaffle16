package com.araffle.araffle.Dao;

import com.araffle.araffle.Entity.User;
import com.araffle.araffle.Entity.UserSession;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSessionDao extends MPJBaseMapper<UserSession> {
}
