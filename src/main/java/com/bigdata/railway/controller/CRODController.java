package com.bigdata.railway.controller;

import com.bigdata.railway.dao.DateAmountMapper;
import com.bigdata.railway.dao.GoodsAmountMapper;
import com.bigdata.railway.dao.OdAmountMapper;
import com.bigdata.railway.dao.TransportAmountMapper;
import com.bigdata.railway.entity.*;
import com.bigdata.railway.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @date 2019/7/16
 * @author JQQ
 */
@RestController
@RequestMapping("/")
public class CRODController {
    @Autowired
    DateAmountMapper dateAmountMapper;
    @Autowired
    TransportAmountMapper transportAmountMapper;
    @Autowired
    GoodsAmountMapper goodsAmountMapper;
    @Autowired
    OdAmountMapper odAmountMapper;


    /**
     * 获取所有日期-货运量记录
     * @return
     */
    @GetMapping("/search/1")
    public List<DateAmount> selectAllDate(){
        return dateAmountMapper.selectByExample(null);
    }

    /**
     * 获取所有货运方式-货运量记录
     * @return
     */
    @GetMapping("/search/2")
    public List<TransportAmount> selectAllTransport(){
        return transportAmountMapper.selectByExample(null);
    }

    /**
     * 获取所有货运品类-货运量记录
     * @return
     */
    @GetMapping("/search/3")
    public List<GoodsAmount> selectAllGoods(){
        return goodsAmountMapper.selectByExample(null);
    }

    /**
     * 获取所有OD区域-货运量记录
     * @return
     */
    @GetMapping("/search/4")
    public List<OdAmount> selectAllOd(){
        return odAmountMapper.selectByExample(null);
    }

    /**
     * 获取所有铁路车站及其货运量
     * @return
     */
    @GetMapping("/search/odAmount")
    public List<Map<String, Object>> getOdAndAmount(){
        List<Map<String, Object>> odAndAmount = new ArrayList<>();
        List<OdAmount> odAmounts = odAmountMapper.selectByExample(null);
        for(OdAmount odAmount : odAmounts){
            Map<String, Object> map = new HashMap<>();
            map.put("name", odAmount.getOd());
            map.put("value", Integer.parseInt(odAmount.getAmount()));
            odAndAmount.add(map);
        }
        return odAndAmount;
    }

    /**
     * 获取各个铁路站的经纬度
     * @return
     */
    @GetMapping("/search/odLocation")
    public Map<String, List<String>> getOdAndLocation(){
        List<OdAmount> odAmounts = odAmountMapper.selectByExample(null);
        Map<String, List<String>> map = new HashMap<>();
        for(OdAmount odAmount : odAmounts){
            List<String> list = new ArrayList<>();
            list.add(odAmount.getLng());
            list.add(odAmount.getLat());
            map.put(odAmount.getOd(), list);
        }
        return map;

    }

    @PostMapping("/search/1")
    public List<DateAmount> selectDateAmount(@RequestBody Map<String, Object> dateAmount) throws ParseException {
        DateAmountExample dateAmountExample = new DateAmountExample();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date indate=sdf.parse(dateAmount.get("date").toString());
        dateAmountExample.createCriteria().andDateEqualTo(indate);
        return dateAmountMapper.selectByExample(dateAmountExample);

    }

    @PostMapping("/search/2")
    public List<TransportAmount> selectTransportAmount(@RequestBody Map<String, Object> transportAmount) throws ParseException {
        TransportAmountExample transportAmountExample = new TransportAmountExample();
        transportAmountExample.createCriteria().andTransportEqualTo(transportAmount.get("transport").toString());
        return transportAmountMapper.selectByExample(transportAmountExample);
    }

    @PostMapping("/search/3")
    public List<GoodsAmount> selectGoodsAmount(@RequestBody Map<String, Object> goodsAmount) throws ParseException {
        GoodsAmountExample goodsAmountExample = new GoodsAmountExample();
        goodsAmountExample.createCriteria().andGoodsEqualTo(goodsAmount.get("goods").toString());
        return goodsAmountMapper.selectByExample(goodsAmountExample);
    }

    @PostMapping("/search/4")
    public List<OdAmount> selectOdAmount(@RequestBody Map<String, Object> odAmount) throws ParseException {
        OdAmountExample odAmountExample = new OdAmountExample();
        odAmountExample.createCriteria().andOdEqualTo(odAmount.get("od").toString());
        return odAmountMapper.selectByExample(odAmountExample);
    }


    @PutMapping("/update/1")
    public Integer updateDateAmount(@RequestBody DateAmount dateAmount) {
        return dateAmountMapper.updateByPrimaryKeySelective(dateAmount);
    }

    @PutMapping("/update/2")
    public Integer updateTransportAmount(@RequestBody TransportAmount transportAmount) {
        return transportAmountMapper.updateByPrimaryKeySelective(transportAmount);
    }

    @PutMapping("/update/3")
    public Integer updateGoodsAmount(@RequestBody GoodsAmount goodsAmount) {
        return goodsAmountMapper.updateByPrimaryKeySelective(goodsAmount);
    }

    @PutMapping("/update/4")
    public Integer updateOdAmount(@RequestBody OdAmount odAmount) {
        return odAmountMapper.updateByPrimaryKeySelective(odAmount);
    }

    @DeleteMapping("/delete/1/{id}")
    public Integer deleteDateAmount(@PathVariable ("id") int id) {
        return dateAmountMapper.deleteByPrimaryKey(id);
    }

    @DeleteMapping("/delete/2/{id}")
    public Integer deleteTransportAmount(@PathVariable ("id") int id) {
        return transportAmountMapper.deleteByPrimaryKey(id);
    }

    @DeleteMapping("/delete/3/{id}")
    public Integer deleteGoodsAmount(@PathVariable ("id") int id) {
        return goodsAmountMapper.deleteByPrimaryKey(id);
    }

    @DeleteMapping("/delete/4/{id}")
    public Integer deleteOdAmount(@PathVariable ("id") int id) {
        return odAmountMapper.deleteByPrimaryKey(id);
    }
}
