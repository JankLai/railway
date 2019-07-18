package com.bigdata.railway.service;

import com.bigdata.railway.dao.DateAmountMapper;
import com.bigdata.railway.dao.GoodsAmountMapper;
import com.bigdata.railway.dao.OdAmountMapper;
import com.bigdata.railway.dao.TransportAmountMapper;
import com.bigdata.railway.entity.DateAmount;
import com.bigdata.railway.entity.GoodsAmount;
import com.bigdata.railway.entity.OdAmount;
import com.bigdata.railway.entity.TransportAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class UploadService {
    @Autowired
    DateAmountMapper dateAmountMapper;
    @Autowired
    TransportAmountMapper transportAmountMapper;
    @Autowired
    GoodsAmountMapper goodsAmountMapper;
    @Autowired
    OdAmountMapper odAmountMapper;

    List<DateAmount> dateList = new ArrayList<>();
    List<TransportAmount> transportList = new ArrayList<>();
    List<GoodsAmount> goodsList = new ArrayList<>();
    List<OdAmount> odList = new ArrayList<>();


    //读取货运量统计文件,type 1->日期_货运量；2->运输方式_货运量；3->货物种类_货运量；4->od区域_货运量
    public boolean readFile(MultipartFile file, int type){
        //首先删除原有的统计数据
        switch (type){
            case 1:
                dateAmountMapper.deleteByExample(null);
                break;
            case 2:
                transportAmountMapper.deleteByExample(null);
                break;
            case 3:
                goodsAmountMapper.deleteByExample(null);
                break;
            case 4:
                System.out.println("删除");
                odAmountMapper.deleteByExample(null);
                break;
            default:
                break;
        }
        //插入统计数据
        try {
            InputStream is = file.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            while(br.ready()){
                String line = br.readLine();
                String strings[] = line.split("\t");
                //
                switch (type){
                    case 1:

                        DateAmount dateAmount = new DateAmount();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        StringBuffer stringBuffer = new StringBuffer(strings[0]);
                        stringBuffer.insert(6,"-");
                        stringBuffer.insert(4,"-");
                        dateAmount.setDate(sdf.parse(stringBuffer.toString()));
                        dateAmount.setAmount(strings[1]);
                        dateList.add(dateAmount);
//                        dateAmountMapper.insertSelective(dateAmount);
                        break;
                    case 2:
                        TransportAmount transportAmount = new TransportAmount();
                        transportAmount.setTransport(strings[0]);
                        transportAmount.setAmount(strings[1]);
                        transportList.add(transportAmount);
//                        transportAmountMapper.insertSelective(transportAmount);
                        break;
                    case 3:
                        GoodsAmount goodsAmount = new GoodsAmount();
                        goodsAmount.setGoods(strings[0]);
                        goodsAmount.setAmount(strings[1]);
                        goodsList.add(goodsAmount);
//                        goodsAmountMapper.insertSelective(goodsAmount);
                        break;
                    case 4:
                        OdAmount odAmount = new OdAmount();
                        odAmount.setOd(strings[0]);
                        odAmount.setAmount(strings[1]);
                        odAmount.setLng(strings[2]);
                        odAmount.setLat(strings[3]);
                        odList.add(odAmount);
//                        odAmountMapper.insertSelective(odAmount);
//                        System.out.println("插入");
                        break;
                    default:
                        System.out.println("type为： "+type);
                        return false;
                }

            }
            if(type == 1){//批量插入，速度快
                dateAmountMapper.batchInsert(dateList);
            }
            else if(type == 2){
                transportAmountMapper.batchInsert(transportList);
            }
            else if(type == 3){
                goodsAmountMapper.batchInsert(goodsList);
            }
            else if(type == 4){//批量插入几千条数据，速度快
                odAmountMapper.batchInsert(odList);
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

}
