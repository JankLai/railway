package com.bigdata.railway.dao;

import com.bigdata.railway.entity.DateAmount;
import com.bigdata.railway.entity.DateAmountExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DateAmountMapper {
    /**
     * 批量插入时间_货运量数据
     * @param dateList
     * @return
     */
    Integer batchInsert(@Param("dateList") List<DateAmount> dateList);

    long countByExample(DateAmountExample example);

    int deleteByExample(DateAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DateAmount record);

    int insertSelective(DateAmount record);

    List<DateAmount> selectByExample(DateAmountExample example);

    DateAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DateAmount record, @Param("example") DateAmountExample example);

    int updateByExample(@Param("record") DateAmount record, @Param("example") DateAmountExample example);

    int updateByPrimaryKeySelective(DateAmount record);

    int updateByPrimaryKey(DateAmount record);
}