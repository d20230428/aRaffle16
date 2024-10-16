package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.GameDao;
import com.araffle.araffle.Entity.Game;
import com.araffle.araffle.Entity.GameSkins;
import com.araffle.araffle.Entity.Skin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameDao gameDao;

//    //查询所有数据
//    public List<GameSkins> selectAll(){
//        MPJLambdaWrapper wrapper = new MPJLambdaWrapper<GameSkins>()
//                .selectAll(Game.class)
//                .selectCollection(Skin.class,GameSkins::getSkins)
//                .leftJoin(Skin.class,Skin::getGameId,Game::getId);
//
//        List<GameSkins> gameSkins = gameDao.selectJoinList(GameSkins.class, wrapper);
//        return gameSkins;
//    }
    //查询所有数据
    public List<Game> selectAll(){
        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        //升序
        queryWrapper.orderByAsc("sort_order");
        return gameDao.selectList(queryWrapper);
    }

    //查询单个数据
    public Game selectById(int id){
        return gameDao.selectById(id);
    }

    //修改数据
    public int update(Game game){
        return gameDao.updateById(game);
    }

    //添加数据
    public int insert(Game game){
        return gameDao.insert(game);
    }

    //删除数据
    public int delete(int id){
        return gameDao.deleteById(id);
    }
}
