package com.araffle.araffle.Dao;

import com.araffle.araffle.Entity.Game;
import com.araffle.araffle.Entity.OnlineRewards;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameDao extends MPJBaseMapper<Game> {
}
