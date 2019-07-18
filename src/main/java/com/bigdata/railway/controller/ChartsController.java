package com.bigdata.railway.controller;

import com.bigdata.railway.dao.DateAmountMapper;
import com.bigdata.railway.dao.GoodsAmountMapper;
import com.bigdata.railway.dao.OdAmountMapper;
import com.bigdata.railway.dao.TransportAmountMapper;
import com.bigdata.railway.entity.DateAmount;
import com.bigdata.railway.entity.GoodsAmount;
import com.bigdata.railway.entity.OdAmount;
import com.bigdata.railway.entity.TransportAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/charts")
public class ChartsController {
    @Autowired
    DateAmountMapper dateAmountMapper;
    @Autowired
    TransportAmountMapper transportAmountMapper;
    @Autowired
    GoodsAmountMapper goodsAmountMapper;
    @Autowired
    OdAmountMapper odAmountMapper;

    @GetMapping("/date")
    public List<DateAmount> getDateAmount(){
        return dateAmountMapper.selectByExample(null);
//        List<DateAmount> dateAmounts= dateAmountMapper.selectByExample(null);
//        for(DateAmount dateAmount : dateAmounts){
//            System.out.println(dateAmount.getDate());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                dateAmount.setDate(sdf.parse(dateAmount.getDate().toString()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return dateAmounts;
    }

    @GetMapping("/transport")
    public List<TransportAmount> getTransportAmount(){
        return transportAmountMapper.selectByExample(null);
    }

    @GetMapping("/goods")
    public List<GoodsAmount> getGoodsAmount(){
        return goodsAmountMapper.selectByExample(null);
    }

    @GetMapping("od")
    public List<OdAmount> getOdAmount(){
        return odAmountMapper.selectByExample(null);
    }

}
