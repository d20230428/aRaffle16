package com.araffle.araffle.Dao;

import com.araffle.araffle.Entity.PrizePool;
import com.araffle.araffle.Entity.User;
import com.araffle.araffle.Service.PrizePoolService;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface PrizePoolDao extends MPJBaseMapper<PrizePool> {
}
