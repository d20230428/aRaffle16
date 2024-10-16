package com.araffle.araffle.Service;

import com.araffle.araffle.Dao.FlashSaleDao;
import com.araffle.araffle.Entity.FlashSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashSaleService {

    @Autowired
    private FlashSaleDao flashSaleDao;

    //查询所有信息
    public List<FlashSale> selectAll() {
        return flashSaleDao.selectList(null);
    }
}
