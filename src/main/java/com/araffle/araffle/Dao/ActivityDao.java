package com.araffle.araffle.Dao;

import com.araffle.araffle.Entity.Activity;
import com.araffle.araffle.Entity.DrawRecord;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityDao extends MPJBaseMapper<Activity> {
}
