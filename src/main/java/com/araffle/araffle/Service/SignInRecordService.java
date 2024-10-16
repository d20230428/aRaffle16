package com.araffle.araffle.Service;

import cn.hutool.core.date.DateUtil;
import com.araffle.araffle.Dao.SignInRecordDao;
import com.araffle.araffle.Entity.SignInRecord;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SignInRecordService {
    @Autowired
    SignInRecordDao signInRecordDao;

    //插入一条数据
    public int insertSignInRecord(int userId, int rewardId) {
        SignInRecord signInRecord = SignInRecord.builder().userId(userId).rewardId(rewardId).signInDate(DateUtil.today()).createdAt(DateUtil.today()).build();
        return signInRecordDao.insert(signInRecord);
    }

    public int checkContinuousSignIn(int userId) throws Exception {
        QueryWrapper<SignInRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<SignInRecord> signInRecordList = signInRecordDao.selectList(queryWrapper);

        if (signInRecordList == null || signInRecordList.isEmpty()) {
            return 0; // 如果没有签到记录，直接返回0
        }

        // 按照签到日期排序
        Collections.sort(signInRecordList, new Comparator<SignInRecord>() {
            @Override
            public int compare(SignInRecord o1, SignInRecord o2) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.parse(o1.getCreatedAt()).compareTo(sdf.parse(o2.getCreatedAt()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // 检查相邻的签到日期是否连续，并记录连续签到的天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int continuousDays = 1; // 初始化连续签到天数为1
        for (int i = 0; i < signInRecordList.size() - 1; i++) {
            try {
                long diff = sdf.parse(signInRecordList.get(i + 1).getCreatedAt()).getTime() -
                        sdf.parse(signInRecordList.get(i).getCreatedAt()).getTime();
                if (diff == 24 * 60 * 60 * 1000) { // 检查日期差是否为1天
                    continuousDays++;
                } else {
                    break; // 如果日期不连续，跳出循环
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        // 返回连续签到天数除以7后的余数
        return continuousDays % 7;
    }

    //查看今天是否签到
    public boolean checkTodaySignIn(int userId) {
        QueryWrapper<SignInRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("sign_in_date", DateUtil.today());
        SignInRecord signInRecordList = signInRecordDao.selectOne(queryWrapper);
        return signInRecordList != null;
    }

    //通过id查询数据
    public SignInRecord selectById(int id) {
        QueryWrapper<SignInRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return signInRecordDao.selectOne(queryWrapper);
    }
}
