package com.bigdata.railway.dao;

import com.bigdata.railway.entity.OdAmount;
import com.bigdata.railway.entity.OdAmountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OdAmountMapper {

    /**
     * 批量插入OD区域_货运量数据
     * @param odAList
     * @return
     */
    Integer batchInsert(@Param("odList") List<OdAmount> odAList);

    int deleteByExample(OdAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OdAmount record);

    int insertSelective(OdAmount record);

    List<OdAmount> selectByExample(OdAmountExample example);

    OdAmount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OdAmount record);

    int updateByPrimaryKey(OdAmount record);
}