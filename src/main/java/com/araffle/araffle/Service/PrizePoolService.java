package com.araffle.araffle.Service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.araffle.araffle.Dao.PrizePoolDao;
import com.araffle.araffle.Entity.Game;
import com.araffle.araffle.Entity.PrizePool;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class PrizePoolService {

    @Autowired
    PrizePoolDao prizePoolDao;

    @Autowired
    DrawRecordService drawRecordService;

    @Autowired
    UserService userService;

    //查询所有数据
    public List<PrizePool> selectAll(){
        QueryWrapper<PrizePool> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        //升序
        queryWrapper.orderByAsc("sort_order");
        List<PrizePool> prizePoolList = prizePoolDao.selectList(queryWrapper);
        return prizePoolList;
    }

    //getPrize
    public PrizePool getPrize(int userId) {
        List<PrizePool> prizePoolList = prizePoolDao.selectList(null);

        // 计算总概率
        BigDecimal totalProbability = BigDecimal.ZERO;
        for (PrizePool prize : prizePoolList) {
            totalProbability = totalProbability.add(prize.getProbability());
        }

        // 生成一个随机数
        Random random = new Random();
        BigDecimal randomValue = new BigDecimal(random.nextDouble()).multiply(totalProbability);

        // 根据随机数选择奖品
        BigDecimal cumulativeProbability = BigDecimal.ZERO;
        for (PrizePool prize : prizePoolList) {
            cumulativeProbability = cumulativeProbability.add(prize.getProbability());
            if (randomValue.compareTo(cumulativeProbability) < 0) {
                drawRecordService.insertDrawRecord(userId, prize.getId());
                return prize;
            }
        }

        // 如果没有找到奖品，返回 null 或默认奖品
        return null;
    }

    public int getAngleSum(int id, int userId){
        QueryWrapper<PrizePool> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<PrizePool> prizePoolList = prizePoolDao.selectList(queryWrapper);
        int angleSum = 0;
        for (PrizePool prizePool : prizePoolList) {
            if (prizePool.getId() == id) {
                int result = userService.addCoin(userId, prizePool.getAngle());
                if (result > 0) {
                    return angleSum;
                }
            } else {
                angleSum += prizePool.getAngle();
            }
        }
        return angleSum;
    }

    //修改数据
    public int update(PrizePool prizePool){
        return prizePoolDao.updateById(prizePool);
    }


    //添加数据
    public int insert(PrizePool prizePool){
        return prizePoolDao.insert(prizePool);
    }

    //删除数据
    public int delete(int id){
        return prizePoolDao.deleteById(id);
    }
}
