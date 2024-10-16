package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.SkinDao;
import com.araffle.araffle.Entity.Skin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SkinService {

    @Autowired
    SkinDao skinDao;

    public static List<Skin> getRandomSample(List<Skin> list, int sampleSize) {
        Random rand = new Random();
        List<Skin> sample = new ArrayList<>();
        for (int i = 0; i < sampleSize; i++) {
            int randomIndex = rand.nextInt(list.size());
            sample.add(list.get(randomIndex));
            // 防止重复选择，可以将选中的元素移除
            list.remove(randomIndex);
        }
        return sample;
    }

    //通过id查询数据
    public List<Skin> selectById(int id) {
        QueryWrapper<Skin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("game_id", id);
        return skinDao.selectList(queryWrapper);
    }

    //查询所有数据
    public List<Skin> selectAll() {
        return skinDao.selectList(null);
    }

//    随机获取10个皮肤
    public List<Skin> selectRandom10() {
        QueryWrapper<Skin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_popular", true);
        List<Skin> allSkins = skinDao.selectList(queryWrapper);
        List<Skin> randomSample = getRandomSample(allSkins, 10);
        return randomSample;
    }

    //修改数据
    public int update(Skin skin) {
        return skinDao.updateById(skin);
    }

    //添加数据
    public int insert(Skin skin) {
        return skinDao.insert(skin);
    }

    //删除数据
    public int delete(int id){
        return skinDao.deleteById(id);
    }

    //通过id查询数据
    public Skin selectById2(int id) {
        return skinDao.selectById(id);
    }
}
