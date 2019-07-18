package com.bigdata.railway.dao;

import com.bigdata.railway.entity.TransportAmount;
import com.bigdata.railway.entity.TransportAmountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransportAmountMapper {
    /**
     * 批量插入货运方式_货运量数据
     * @param transportList
     * @return
     */
    Integer batchInsert(@Param("transportList") List<TransportAmount> transportList);

    long countByExample(TransportAmountExample example);

    int deleteByExample(TransportAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransportAmount record);

    int insertSelective(TransportAmount record);

    List<TransportAmount> selectByExample(TransportAmountExample example);

    TransportAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransportAmount record, @Param("example") TransportAmountExample example);

    int updateByExample(@Param("record") TransportAmount record, @Param("example") TransportAmountExample example);

    int updateByPrimaryKeySelective(TransportAmount record);

    int updateByPrimaryKey(TransportAmount record);
}