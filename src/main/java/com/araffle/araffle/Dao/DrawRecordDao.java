package com.araffle.araffle.Dao;

import com.araffle.araffle.Entity.DrawRecord;
import com.araffle.araffle.Entity.PrizePool;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrawRecordDao extends MPJBaseMapper<DrawRecord> {
}
